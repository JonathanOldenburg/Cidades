package br.com.xptosystems.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.multipart.FormDataParam;

import br.com.xptosystems.service.CityService;

@Path("/")
public class CityApi {

    @POST
    @Path("/uploadCities")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(
                        @FormDataParam("file") InputStream file) {
        return new CityService().importCsvFile(file);
    }
}
