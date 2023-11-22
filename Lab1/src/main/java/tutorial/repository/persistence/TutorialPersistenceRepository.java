package tutorial.repository.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import skill.entity.Skill;
import tutorial.entity.Tutorial;
import tutorial.repository.api.TutorialRepository;
import user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Skill entity. Repositories should be used in business layer (e.g.: in services). The request
 * scope is a result of the fact that {@link EntityManager} objects cannot be used in multiple threads (are not thread
 * safe). Because services are CDI application scoped beans (technically singletons) then repositories must be thread
 * scoped in order to ensure single entity manager for single thread.
 */
@Dependent
public class TutorialPersistenceRepository implements TutorialRepository {

    /**
     * Connection with the database (not thread safe).
     */
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Tutorial> find(UUID id) {
        return Optional.ofNullable(em.find(Tutorial.class, id));
    }

    @Override
    public List<Tutorial> findAll() {
        return em.createQuery("select t from Tutorial t", Tutorial.class).getResultList();
    }

    @Override
    public void create(Tutorial entity) {
        em.persist(entity);
    }

    @Override
    public void update(Tutorial entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Tutorial entity) {
        em.remove(em.find(Tutorial.class, entity.getId()));
    }

    @Override
    public List<Tutorial> findAllByUser(User user) {
        return em.createQuery("select t from Tutorial t where t.user = :user", Tutorial.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Tutorial> findAllBySkill(Skill skill) {
        return em.createQuery("select t from Tutorial t where t.skill = :skill", Tutorial.class)
                .setParameter("skill", skill)
                .getResultList();
    }

    @Override
    public Optional<Tutorial> findBySkillAndId(Skill skill, UUID id) {
        try {
            return Optional.of(em.createQuery("select t from Tutorial t where t.id = :id and t.skill = :skill", Tutorial.class)
                    .setParameter("skill", skill)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Tutorial> findByUserAndId(User user, UUID id) {
        try {
            return Optional.of(em.createQuery("select t from Tutorial t where t.id = :id and t.author = :author", Tutorial.class)
                    .setParameter("author", user)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
