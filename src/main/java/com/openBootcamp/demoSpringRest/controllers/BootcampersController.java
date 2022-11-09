package com.openBootcamp.demoSpringRest.controllers;

import com.openBootcamp.demoSpringRest.models.Bootcamper;
import com.openBootcamp.demoSpringRest.services.BootcamperService;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.List;


@Component
@Path("/")
public class BootcampersController {

    private BootcamperService bootcamperService;


    public BootcampersController(BootcamperService bootcamperService){
        this.bootcamperService = new BootcamperService();

        this.bootcamperService.add(new Bootcamper("Gaston"));
        this.bootcamperService.add(new Bootcamper("Gonzalo"));

        System.out.println(bootcamperService.getAll());
    }


    @GET
    @Path("/bootcampers")
    @Produces("application/json")
    public List<Bootcamper> listarTodos(){
        return bootcamperService.getAll();
    }
    @GET
    @Path("/bootcampers/{name}")
    @Produces("application/json")
    public Bootcamper getUserByName(@PathParam("name") String name){
        return bootcamperService.get(name);
    }

    @POST
    @Path("/bootcampers")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addBootcamper(Bootcamper bootcamper) {
        bootcamperService.add(bootcamper);
        return Response.created(
                URI.create("/bootcampers" + bootcamper.getName())
        ).build();
    }




}
