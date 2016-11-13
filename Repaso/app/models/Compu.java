package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Compu extends Model{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    
    @Constraints.Required(message="Debe ingresar un nombre.")
    public String nombre;
    
    @Constraints.Required(message="Debe ingresar la descripción.")
    public String descripcion;

   
	public static Find<Long,Compu> find = new Find<Long,Compu>(){};

    public static PagedList<Compu> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("nombre", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagedList(page, pageSize);
    }
}
