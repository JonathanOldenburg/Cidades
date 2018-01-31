package br.com.xptosystems.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    
    @GET
    @Path("/getCapitals")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapitals() {
        return new CityService().getCapitals();
    }
    
    @GET
    @Path("/getStateWithMoreCities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStateWithMoreCities() {
        return new CityService().getStateWithMoreCities();
    }
    
    @GET
    @Path("/getStateWithLessCities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStateWithLessCities() {
    	return new CityService().getStateWithLessCities();
    }
    
    @GET
    @Path("/getStateInfo/{uf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStateInfo( @PathParam("uf") String uf) {
    	return new CityService().getStateInfo(uf);
    }
}
