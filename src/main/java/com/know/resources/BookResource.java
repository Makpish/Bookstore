package com.know.resources;

import com.know.pojo.NameInput;
import com.know.pojo.Pair;
import com.know.pojo.ResponseType;
import com.know.services.BookService;
import com.know.utils.ResponseBuilder;
import com.know.utils.Utils;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class BookResource {

    private final BookService bookService;

    private final Utils utils = new Utils();

    @Inject
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @UnitOfWork
    public Response getAllBooks(@QueryParam("pageNumber") @Nullable IntParam pageNumber,
                                @QueryParam("pageSize") @Nullable IntParam pageSize) {
        ResponseType response = new ResponseType();
        try {
            Pair<Long, Long> pageDigits = utils.processPageLimit(pageNumber, pageSize);
            long limit = pageDigits.getSecond();
            long offset = pageDigits.getFirst();
            response.setValue(bookService.getAllBooks(offset, limit),
                    bookService.countAllBooks(), offset, limit);
            return ResponseBuilder.getResponse(Response.Status.OK, response);
        } catch (Exception e) {
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }

    @GET
    @UnitOfWork
    @Path("/{bookId}")
    public Response getBook0(@PathParam("bookId") LongParam bookId) {
        ResponseType response = new ResponseType();
        try {
            response.setData(bookService.getBook(bookId.get()));
            if (response.getData() != null)
                response.setCount(1);
            return ResponseBuilder.getResponse(Response.Status.OK, response);
        } catch (Exception e) {
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(@NotNull @Valid NameInput input) {
        ResponseType response = new ResponseType();
        try {
            response.setData(bookService.createBook(input.getName(), input.getAuthorId()));
            if (response.getData() != null)
                response.setCount(1);
            return ResponseBuilder.getResponse(Response.Status.OK, response);
        } catch (Exception e) {
            response.setError(e.toString());
            return ResponseBuilder.getResponse(Response.Status.INTERNAL_SERVER_ERROR, response);
        }
    }
}
