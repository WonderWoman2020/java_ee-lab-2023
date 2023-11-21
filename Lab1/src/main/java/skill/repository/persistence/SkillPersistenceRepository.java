package skill.repository.persistence;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import skill.entity.Skill;
import skill.repository.api.SkillRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Skill entity. Repositories should be used in business layer (e.g.: in services). The request
 * scope is a result of the fact that {@link EntityManager} objects cannot be used in multiple threads (are not thread
 * safe). Because services are CDI application scoped beans (technically singletons) then repositories must be thread
 * scoped in order to ensure single entity manager for single thread.
 */
@RequestScoped
public class SkillPersistenceRepository implements SkillRepository {

    /**
     * Connection with the database (not thread safe).
     */
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Skill> find(UUID id) {
        return Optional.ofNullable(em.find(Skill.class, id));
    }

    @Override
    public List<Skill> findAll() {
        return em.createQuery("select s from Skill s", Skill.class).getResultList();
    }

    @Override
    public void create(Skill entity) {
        em.persist(entity);
    }

    @Override
    public void update(Skill entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Skill entity) {
        /* Clearing cache used as workaround when not handling both sides of relationships, not recommended. */
//        em.getEntityManagerFactory().getCache().evictAll(); //Clearing 2nd level cache.
//        em.clear(); //Clearing 1st level cache.
        em.remove(em.find(Skill.class, entity.getId()));
    }
}
