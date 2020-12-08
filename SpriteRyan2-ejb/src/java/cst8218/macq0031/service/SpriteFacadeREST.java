/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.macq0031.service;

import cst8218.macq0031.entity.Sprite;
import cst8218.macq0031.entity.AbstractFacade;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Restful services for sprite manipulations - not implemented correctly
 * @author ryan macqueen
 */
@Stateless
@DeclareRoles({"admin","REST"})
@RolesAllowed({"admin","REST"})
@Path("cst8218.macq0031.entity.Sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "SpriteRyan2-ejbPU")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }
    /**
     *  create a sprite in the db
     * @param entity  the sprite object to pass to the db
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Sprite entity) {
        if (super.find(entity.getId()) != null && entity.getId() != null) {
           Sprite wrapper = super.find(entity.getId());
           entity.updates(wrapper);
           super.edit(wrapper);
        }
        super.create(entity);
    }
     /**
     * 
     * Preserves unchanged data
     * @param id the id of the sprite to be edited
     * @param entity the new sprite data to replace the original sprite
     */
    
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit2(@PathParam("id") Long id, Sprite entity) {
        Sprite wrapper = super.find(id);
        entity.updates(wrapper);
        super.edit(wrapper);
    }
    /**
     * 
     * @param id the id of the sprite to be edited
     * @param entity the new sprite data to replace the original sprite
     */
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Sprite entity) {
        
        Sprite wrapper = new Sprite(500,500); // generate new sprite (we do not keep previous data in put request)
        entity.updates(wrapper);
        super.edit(wrapper);
    }
    /**
     *  Delete the sprite with id
     * @param id 
     */
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    /**
     * 
     * @param id the sprite id
     * @return the sprite entity with 'id'
     */
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }
    /**
     * 
     * @return returns a list of all the sprites
     */
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }
    /**
     * 
     * @param from starting id
     * @param to ending id
     * @return  a List of all sprites in the range between from and to
     */
  
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    /**
     *  
     * @return the count of all sprite entities as a string
     */
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    /**
     * get the entityManager
     * @return 
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
