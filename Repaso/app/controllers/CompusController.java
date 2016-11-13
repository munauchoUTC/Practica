package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;

import views.html.Compus.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import models.*;

public class CompusController extends Controller{
	private FormFactory formFactory;

    @Inject
    public CompusController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

	public Result index()
	{
		return ok (index.render());
		//return GO_INDEX;
	}
	
	public Result create()
	{
		Form<Compu> personaForm = formFactory.form(Compu.class);
		return ok(create.render(personaForm));
	}
	
	public Result save()
	{
		Form<Compu> personaForm = formFactory.form(Compu.class).bindFromRequest();
		if(personaForm.hasErrors())
		{
			return badRequest(create.render(personaForm));
		}
		personaForm.get().save();
		return redirect("/libros/index");
		//return GO_INDEX;
	}
	
	public Result list(int page, String sortBy, String order, String filter) 
	{
        return ok(
            		list.render(Compu.page(page, 10, sortBy, order, filter) )
        		);
    }

    //redirige a la lista de datos de la tabla
    public Result GO_INDEX = Results.redirect(
        routes.LibrosController.list(0, "nombre", "asc", "")
    );
	
    public Result edit(Long id) {
        Form<Compu> personaForm = formFactory.form(Compu.class).fill( Compu.find.byId(id) );
        return ok(
            edit.render(id, personaForm)
        );
    }
    
    
    public Result update(Long id) 
    {
        Form<Compu> personaForm = formFactory.form(Compu.class).bindFromRequest();
        if(personaForm.hasErrors()) {
            return badRequest(edit.render(id, personaForm));
        }

        Transaction txn = Ebean.beginTransaction();
        try {
        	Compu savedLibro = Compu.find.byId(id);
            if (savedLibro != null) {
            	Compu newLibroData = personaForm.get();
                savedLibro.nombre = newLibroData.nombre;
                savedLibro.descripcion = newLibroData.descripcion;

                savedLibro.update();
                txn.commit();
            }
        } finally {
            txn.end();
        }

        return GO_INDEX;
    }
    
    public Result delete(Long id) {
    	Compu.find.ref(id).delete();
        return GO_INDEX;
    }

}
