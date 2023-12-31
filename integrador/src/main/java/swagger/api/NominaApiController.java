package swagger.api;

import swagger.model.Actividad;
import swagger.model.Persona;
import swagger.model.Socio;
import swagger.services.NominaSociosService;
import swagger.services.OfertaActividadesService;
import testing.CupoExcedidoException;
import testing.EdadInsuficienteException;
import testing.NominaSocios;
import testing.YaExisteSocioException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-30T14:23:23.357295482Z[GMT]")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NominaApiController implements NominaApi {

    private static final Logger log = LoggerFactory.getLogger(NominaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public NominaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> nominaAsociarPost(
            @Parameter(in = ParameterIn.DEFAULT, description = "Datos de la persona a agregar a la nómina", required = true, schema = @Schema()) @Valid @RequestBody Persona body) {

        System.out.println(body);
        if (body == null)
            throw new NotAcceptableException("Los datos de la persona son requeridos");
        if (body.getDni() == null
                || body.getNombre() == null
                || body.getApellido() == null
                || body.getEdad() == 0)
            throw new NotAcceptableException("Nombre, apellido, dni y edad de la persona son requeridos");

        try {
            NominaSociosService.Asociar(body);
            Map<String, Object> response = new HashMap<>();

            response.put("mensaje", body.getNombreCompleto() + " ha sido asociado con éxito al club");
            
            log.info("Agregado con éxito");

            return new ResponseEntity(response, HttpStatus.OK);

        } catch (YaExisteSocioException ce) {
            log.error("El socio " + body.getNombreCompleto() + " ya se encuentra inscripto en el club", ce);
            throw new NotAcceptableException(
                    "El socio " + body.getNombreCompleto() + " ya se encuentra inscripto en el club");
        } catch (EdadInsuficienteException ei) {
            log.error( "La edad de " + body.getNombreCompleto() + " es insuficiente ser socio", ei);

            throw new NotAcceptableException(
                    "La edad de " + body.getNombreCompleto() + " es insuficiente ser socio");
        }
    }

    public ResponseEntity<List<Socio>> nominaListGet() {
        List<Socio> nomina = NominaSociosService.GetNomina();
        return new ResponseEntity(nomina, HttpStatus.OK);
    }
}
