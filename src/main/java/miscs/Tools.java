package miscs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static final class IOTools{
        public static byte[] fileToByteArray(File file) {

            try {
                ByteBuffer bb = null;

                FileInputStream fis;
                FileChannel fc;

                fis = new FileInputStream(file);
                fc = fis.getChannel();

                bb = ByteBuffer.allocate((int) file.length());

                fc.read(bb);

                fis.close();
                fc.close();

                return bb.array();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

    }

    public static final class MiscTools {

        public static long randomizeNumber(long downLimitIncluded, long upLimitExcluded) {
            return (long) ((Math.random() * (upLimitExcluded - downLimitIncluded)) + downLimitIncluded);
        }

        public static RunEnvironment getRunningEnvironment() {
            String classPath = null;
            try {
                classPath = new File(Tools.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

                if (classPath.contains("\\WEB-INF\\"))
                    return RunEnvironment.WAR;

                if (classPath.endsWith("target\\classes"))
                    return RunEnvironment.IDE;

                if (classPath.endsWith(".jar"))
                    return RunEnvironment.JAR;

                throw new Exception();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        public static ArrayList<String> readTextFileAsListOfStrings(File txtFile) {
            String strLine;
            ArrayList<String> list = new ArrayList<String>();

            DataInputStream in = null;
            FileInputStream fstream;
            BufferedReader br = null;

            try {
                fstream = new FileInputStream(txtFile);
                in = new DataInputStream(fstream);

                br = new BufferedReader(new InputStreamReader(in, "UTF-8"));


                while ((strLine = br.readLine()) != null)
                    list.add(strLine);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        public static String readTextFileAString(File txtFile) {
            String strLine;

            DataInputStream in = null;
            FileInputStream fstream;
            BufferedReader br = null;
            StringBuffer sb = new StringBuffer();
            try {
                fstream = new FileInputStream(txtFile);
                in = new DataInputStream(fstream);

                br = new BufferedReader(new InputStreamReader(in, "UTF-8"));


                while ((strLine = br.readLine()) != null)
                    sb.append(strLine);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }

    public static final class ServletTools {

        public static String getSimplyWebPage(String urlAddress) {

            StringBuffer sBuffer = new StringBuffer();
            String inputLine;
            BufferedReader in = null;

            try {
                URL url = new URL(urlAddress);
                URLConnection urlc = url.openConnection();

                urlc.addRequestProperty("User-Agent", Globals.USER_AGENT);

                in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

                while ((inputLine = in.readLine()) != null)
                    sBuffer.append(inputLine).append("\n");

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (in != null && in.ready())
                        in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return sBuffer.toString();
        }

    }

    public static final class DBTools {

        public static <T> T findByField(Class<T> entityClass, String fieldName, Object valueToSearch, EntityManager entityManager) {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            Predicate predicate = cb.equal(root.get(fieldName), valueToSearch);

            query.where(predicate);
            final List<T> resultList = entityManager.createQuery(query).setMaxResults(1).getResultList();

            if (resultList.size() == 0)
                return null;

            return resultList.get(0);
        }

        public static <T> T findByTwoFields(Class<T> entityClass, String fieldAName, Object valueA, String fieldBName, Object valueB, EntityManager entityManager) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            Predicate predicateA = cb.equal(root.get(fieldAName), valueA);
            Predicate predicateB = cb.equal(root.get(fieldBName), valueB);
            Predicate predicateC = cb.and(predicateA, predicateB);

            query.where(predicateC);
            final List<T> resultList = entityManager.createQuery(query).setMaxResults(1).getResultList();

            if (resultList.size() == 0)
                return null;

            return resultList.get(0);
        }

        public static void persistEntity(Serializable entityToPersist, EntityManager entityManager) {

            try {
                final EntityTransaction transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.persist(entityToPersist);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public static void removeEntity(Serializable entityToRemove, EntityManager entityManager) {
            final EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityToRemove);
            transaction.commit();

        }

        public static <T> ArrayList<T> getAllEntities(EntityManager entityManager, T entityType){

            final List<?> resultList = entityManager.createQuery("SELECT a FROM " + entityType.getClass().getName() + " a", entityType.getClass()).getResultList();

            ArrayList<T> rList=new ArrayList<>();

            for(Object obj:resultList)
                rList.add((T) obj);

            return rList;

        }




    }



}
