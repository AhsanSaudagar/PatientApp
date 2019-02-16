package com.perennial.patientapp.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class PatientAppDAOImpl {


    @Autowired
    private SessionFactory sessionFactory;

    public PatientAppDAOImpl(SessionFactory sessionFactory) {
        System.out.println("Init factory");
        this.sessionFactory = sessionFactory;
        System.out.println(sessionFactory);

    }
    
    
}
