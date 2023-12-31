package swagger.api;

import swagger.model.Actividad;
import swagger.services.OfertaActividadesService;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T13:24:22.602477450Z[GMT]")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ActividadesApiController implements ActividadesApi {

    private static final Logger log = LoggerFactory.getLogger(ActividadesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    public ResponseEntity<Void> actividadesCrearPost(
            @Parameter(in = ParameterIn.DEFAULT, description = "Datos de actividad a crear", required = true, schema = @Schema()) @Valid @RequestBody Actividad body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @org.springframework.beans.factory.annotation.Autowired
    public ActividadesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Actividad>> actividadListGet() {
        ArrayList<Actividad> acts = OfertaActividadesService.GetNominaActividades();
        if (acts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(acts);
    }

}
