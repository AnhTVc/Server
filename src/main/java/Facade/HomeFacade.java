package Facade;

import DAO.HomeDAO;
import DAO.HomeDAOTest;
import objects.Home;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.neo4j.graphdb.GraphDatabaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by nguyennhunai on 2016-05-05.
 */
public class HomeFacade {

    private static final Logger logger = Logger.getLogger(HomeFacade.class);
    Map<String,ArrayList<Home>> postBuffer = new HashMap<>();


    int  corePoolSize  =    4;
    int  maxPoolSize   =   5;
    long keepAliveTime = 100;

    ExecutorService executorService =
            new ThreadPoolExecutor(
                    corePoolSize,
                    maxPoolSize,
                    keepAliveTime,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>()
            );



//    public ArrayList<Home> responseHomeFacade(Request request, GraphDatabaseService graphDB) {
    public ArrayList<Home> responseHomeFacade(Request request) {


        ArrayList<Home> result = new ArrayList<>();

        String idUser = request.getParameter("id_user");
        String counter = request.getParameter("counter");
        String timeLast = request.getParameter("time_last");
        String timeNew = request.getParameter("time_new");
        String type = request.getParameter("type");

        Boolean requestContructor = (idUser!=null) ;
        Boolean requestNext = (idUser!=null && counter!=null && timeLast!=null) ;
        Boolean requestCheckNew = (idUser!=null && timeNew!=null && type!=null && type=="check") ;
        Boolean requestShowNew = (idUser!=null && timeNew!=null && type!=null && type=="show") ;

        if (requestCheckNew ) {
            HomeDAO homeDAO = new HomeDAO();

        } else {
            if (requestNext ) {
                HomeDAO homeDAO = new HomeDAO();
            } else {
                if (requestShowNew ) {
                    HomeDAO homeDAO = new HomeDAO();
                } else {
                    if (requestContructor ) {

                        HomeDAOTest homeDAOTest = new HomeDAOTest();
                        System.out.println("check check  1");
                        result.addAll(homeDAOTest.responseHome(idUser,10));

//                        executorService.execute(new Runnable() {
//                            public void run() {
//                                System.out.println("check check  2");
//                                System.out.println("idUser : " +idUser);
//                                result.addAll(homeDAOTest.responseHome(idUser,10));
//                            }
//                        });
//                        postBuffer.remove(idUser);
//                        executorService.execute(new Runnable() {
//                            public void run() {
//                                postBuffer.put(idUser,homeDAOTest.responseHome(idUser,20, 1 ));
//                            }
//                        });


                    }

                }

            }

            logger.info(" Not have params idUser! ");
            System.out.println("postBufeer : " + postBuffer.get(idUser));

        }


        return result;

    }
}