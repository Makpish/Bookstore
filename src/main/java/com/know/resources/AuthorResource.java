package com.know.resources;

import com.know.pojo.NameInput;
import com.know.pojo.Pair;
import com.know.pojo.ResponseType;
import com.know.services.AuthorService;
import com.know.utils.ResponseBuilder;
import com.know.utils.Utils;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class AuthorResource {

    private final AuthorService authorService;

    private final Utils utils = new Utils();

    private static Logger log = LoggerFactory.getLogger(AuthorResource.class);

    @Inject
    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GET
    @UnitOfWork
    public Response getAllAuthors(@QueryParam("pageNumber") @Nullable IntParam pageNumber,
                                  @QueryParam("pageSize") @Nullable IntParam pageSize) {
        ResponseType response = new ResponseType();
        try {
            Pair<Long, Long> pageDigits = utils.processPageLimit(pageNumber, pageSize);
            long limit = pageDigits.getSecond();
            long offset = pageDigits.getFirst();
            response.setValue(authorService.getAllAuthors(offset, limit),
                    authorService.countAllAuthors(), offset, limit);
            return ResponseBuilder.getResponse(Response.Status.OK, response);
        } catch (Exception e) {
            log.error("some error", e);
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }

    @GET
    @UnitOfWork
    @Path("/{authorId}")
    public Response getAuthor(@PathParam("authorId") LongParam authorId) {
        ResponseType response = new ResponseType();
        try {
            response.setData(authorService.getAuthor(authorId.get()));
            if (response.getData() != null)
                response.setCount(1);
            return ResponseBuilder.getResponse(Response.Status.OK, response);
        } catch (Exception e) {
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }

    @GET
    @UnitOfWork
    @Path("/{authorId}/books")
    public Response getAuthorBooks(@PathParam("authorId") LongParam authorId,
                                   @QueryParam("pageNumber") @Nullable IntParam pageNumber,
                                   @QueryParam("pageSize") @Nullable IntParam pageSize) {
        ResponseType response = new ResponseType();
        try {
            Pair<Long, Long> pageDigits = utils.processPageLimit(pageNumber, pageSize);
            long limit = pageDigits.getSecond();
            long offset = pageDigits.getFirst();
            response.setValue(authorService.getAuthorBooks(authorId.get(), offset, limit),
                    authorService.countAuthorBooks(authorId.get()), offset, limit);
            return ResponseBuilder.getResponse(Response.Status.OK, response);
        } catch (Exception e) {
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(@NotNull @Valid NameInput input) {
        ResponseType response = new ResponseType();
        try {
            if (input.getName() == null || input.getName().trim().equals("")) {
                response.setError("Invalid Name");
                return ResponseBuilder.getResponse(Response.Status.BAD_REQUEST, response);
            }
            response.setData(authorService.createNewAuthor(input.getName()));
            if (response.getData() != null)
                response.setCount(1);
            return ResponseBuilder.getResponse(Response.Status.CREATED, response);
        } catch (Exception e) {
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }
}
