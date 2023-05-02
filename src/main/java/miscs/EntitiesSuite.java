package miscs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public enum EntitiesSuite {
    AgamRimon___MainSchema___OnRemote___FromLocal("AgamRimon---MainSchema---OnRemote---FromLocal"),
    AgamRimon___MainSchema___OnLocal___FromLocal("AgamRimon---MainSchema---OnLocal---FromLocal"),
    AgamRimon___MainSchema___OnRemote___FromRemote("AgamRimon---MainSchema---OnRemote---FromRemote"),
    ;

    private EntityManager em;
    private final String persistenceUnitName;


    EntitiesSuite(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    public synchronized EntityManager getEntityManager() {

        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            em = emf.createEntityManager();
        }

        return em;
    }


}
