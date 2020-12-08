/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.macq0031.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ryan
 */
@javax.ejb.Stateless
public class AppuserFacade extends AbstractFacade<Appuser> {

    @PersistenceContext(unitName = "SpriteRyan2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppuserFacade() {
        super(Appuser.class);
    }
    
}
