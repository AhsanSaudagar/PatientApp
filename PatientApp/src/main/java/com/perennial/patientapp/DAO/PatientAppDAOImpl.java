package com.perennial.patientapp.DAO;

import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.util.KeyValue;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class PatientAppDAOImpl<S> {


    @Autowired
    private SessionFactory sessionFactory;

    public PatientAppDAOImpl(SessionFactory sessionFactory) {
        System.out.println("Init factory");
        this.sessionFactory = sessionFactory;
        System.out.println(sessionFactory);

    }


    private static Criteria initCriteria(Class cls, List<KeyValue> conditions, Session session) {
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(cls);
        if (conditions != null) {
            for (KeyValue kv : conditions) {
                criteria.add(Restrictions.eq(kv.getKey(), kv.getValue()));
            }
        }
        return criteria;
    }

    public S getByConditions(Class cls, List<KeyValue> conditions) throws VCare {
        Session session = null;
        S s = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = initCriteria(cls, conditions, session);
            s = (S) criteria.uniqueResult();
            commitSession(session);
        } catch (HibernateException he) {
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return s;
    }

    public S getById(Class cls, Serializable id) throws VCare {
        S s = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.flush();
            session.clear();
            session.getTransaction().begin();
            s = (S) session.get(cls, id);
            commitSession(session);
        } catch (HibernateException he) {
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.flush();
                session.close();

            }
        }
        return s;
    }

    public Long save(S s) throws VCare {
        Session session = null;
        Long id = 0l;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            id = (Long) session.save(s);
            commitSession(session);
        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void saveOrUpdate(S s) throws VCare {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate((s));
            commitSession(session);
        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Object> executeHqlQuery(String sQuery, List<KeyValue> conditions) throws VCare {
        Session session = null;
        List<Object> list = null;

        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query query = session.createQuery(sQuery);
            if (conditions != null) {
                for (KeyValue kv : conditions) {
                    query.setParameter(kv.getKey(), kv.getValue());
                }
            }
            list = query.list();
            commitSession(session);
            if (list == null || list.isEmpty()) {
                list = null;
            }
            return list;
        } catch (HibernateException he) {
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Object[]> executeSqlQuery(String sQuery, List<KeyValue> conditions) throws VCare {
        Session session = null;
        List<Object[]> list = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            SQLQuery query = session.createSQLQuery(sQuery);
            if (conditions != null) {
                for (KeyValue kv : conditions) {
                    query.setParameter(kv.getKey(), kv.getValue());
                }
            }
            list = query.list();
            commitSession(session);
            if (list == null) {
                list = null;
            }
            return list;
        } catch (HibernateException he) {
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(S s) throws VCare {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.delete(s);

            commitSession(session);
        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public void deleteById(Class cls, Serializable id) throws VCare {
        Session session = null;
        S s = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            s = (S) session.load(cls, id);
            if (s != null) {
                session.delete(s);
            }

            commitSession(session);
        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public S merge(S s) throws VCare {
        Session session = null;
        S s1 = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            s1 = (S) session.merge(s);
            commitSession(session);
        } catch (HibernateException he) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new VCare(he.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return s1;
    }

    private void commitSession(Session session) {
        if (!session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
    }
}
