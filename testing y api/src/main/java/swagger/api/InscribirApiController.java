package swagger.api;

import swagger.model.Actividad;
import swagger.model.InscribirBody;
import swagger.model.Socio;
import swagger.services.OfertaActividadesService;
import testing.CupoExcedidoException;
import testing.EdadInsuficienteException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T01:15:12.905934647Z[GMT]")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class InscribirApiController implements InscribirApi {

    private static final Logger log = LoggerFactory.getLogger(InscribirApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public InscribirApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> inscribirPost(
            @Parameter(in = ParameterIn.DEFAULT, description = "Datos del socio y actividad a inscribir", required = true, schema = @Schema()) @Valid @RequestBody InscribirBody body) {
        if (body.getActividad() == null)
            throw new NotAcceptableException("La actividad es requerida");

        Socio socio = body.getSocio();
        if (socio == null)
            throw new NotAcceptableException("El socio es requerido");

        String nombreActividad = body.getActividad().getNombre();
        if (nombreActividad == null)
            throw new NotAcceptableException("El nombre de la actividad es requerido");
        if (socio.getPersona() == null)
            throw new NotAcceptableException("Los datos de la persona son requeridos");
        if (socio.getPersona().getDni() == null
                || socio.getPersona().getNombre() == null
                || socio.getPersona().getApellido() == null
                || socio.getPersona().getEdad() == 0)
            throw new NotAcceptableException("Nombre, apellido, dni y edad de la persona son requeridos");

        Actividad act = OfertaActividadesService.getActividadPorNombre(body.getActividad().getNombre());
        if (act == null)
            throw new NotAcceptableException("La actividad " + nombreActividad + " no existe");

        try {
            act.addInscriptosItem(socio);
            Map<String, Object> response = new HashMap<>();


            response.put("mensaje", socio.getNombreCompleto() + " ha sido inscripto con éxito a " + act.getNombre());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (CupoExcedidoException ce) {
            throw new NotAcceptableException(
                    "El cupo máximo para la actividad " + nombreActividad + " ha sido alcanzado");
        } catch (EdadInsuficienteException ei) {
            throw new NotAcceptableException(
                    "La edad de " + socio.getNombreCompleto() + " es insuficiente para la actividad elegida");
        }
    }

}
