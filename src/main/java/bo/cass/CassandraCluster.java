//package bo.cass;
//
//import com.datastax.driver.core.Cluster;
//import com.datastax.driver.core.Session;
//import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
//import org.apache.log4j.Logger;
//import Config.Constant;
//
///**
// * Created by thuandq on 21/09/2015.
// */
//public class CassandraCluster {
//    private static volatile CassandraCluster instance = null;
//    private Cluster cluster;
//    private Session session;
//    static Logger logger = Logger.getLogger(CassandraCluster.class);
//
//    public Session getSession() {
//        return session;
//    }
//
//    private CassandraCluster() {
//
//
//        String keySpace = null;
//        String seedCassAddress = null;
//        try {
//            keySpace = Constant.KEYSPACE;
//            seedCassAddress = Constant.CLUSTER_ADDRESS;
//        } catch (Exception e) {
//            logger.fatal("error when init configuration keyspace name! quit");
//            System.exit(1);
//        }
//
//        cluster = Cluster.builder()
//                .addContactPoint(seedCassAddress)
//                .withPort(9042)
//                .withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
//                .build();
//
//
//        session = cluster.connect(keySpace);
//    }
//
//    public static CassandraCluster getInstance() {
//        if (instance == null) {
//            synchronized (CassandraCluster.class) {
//                if (instance == null) {
//                    instance = new CassandraCluster();
//                }
//            }
//        }
//        return instance;
//    }
//
//}