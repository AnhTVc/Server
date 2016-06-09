package service;


import Facade.HomeFacade;
import Facade.PostFacade;
import Facade.TagFacade;
import com.google.gson.Gson;
import neo4j.EmbeddedGraph;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.neo4j.graphdb.GraphDatabaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * Created by Administrator on 21/11/2014.
 */
public class Handler extends AbstractHandler {


    private static final Logger logger = Logger.getLogger(Handler.class);
//    GraphDatabaseService graphDB = new EmbeddedGraph().embeddedGraph();


    int  corePoolSize  =    1;
    int  maxPoolSize   =   1;
    long keepAliveTime = 5000;

    ExecutorService executorService =
            new ThreadPoolExecutor(
                    corePoolSize,
                    maxPoolSize,
                    keepAliveTime,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>()
            );




    @Override
    public void handle(String pathRequest, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        Future future = executorService.submit(new Callable() {
            public String call() throws Exception {
                 return executiveHandle(pathRequest, request, httpServletRequest, httpServletResponse);
            }
        });

        try {
            logger.info("future.get() : " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            logger.error(" future not available when request : " + pathRequest);
        }
    }

public String executiveHandle(String pathRequest, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);


        List results = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println("\n\n\n" + pathRequest + "\n\n\n");

        Gson gson = new Gson();
        String gsonReport = "";


        try {

            switch (pathRequest) {

                case "/": {
                    System.out.println("show home");
                    HomeFacade homeFacade = new HomeFacade();
                    results.addAll(homeFacade.responseHomeFacade(request));
                    gsonReport = gson.toJson(results);
                    break;
                }

                case "/post": {   // show 1 post
                    System.out.println("show 1 post");
                    PostFacade postFacade = new PostFacade();
//                    results.addAll(postFacade.showPostFacade(request, graphDB));
                    gsonReport = gson.toJson(results);
                    break;
                }

                case "/tag": { // show list post in tag

                        TagFacade tagFacade = new TagFacade();
//                        results.addAll(tagFacade.showTagFacade(request,graphDB));
                        gsonReport = gson.toJson(results);
                        break;
                }
//                    case "/post/click": {
//                        System.out.println("click");
//                        Boolean bolean = PostFacade.clickPostFacade(request);
//                        results.add(bolean);
//                        gsonReport = gson.toJson(results);
//                        System.out.println(results + "\n" + request + bolean.toString());
//                        break;
//                    }
//
//                    //tag
//                    case "/tag/follow": {
//                        System.out.println("follow tag");
//                        Boolean bolean = TagFacade.followTagFacade(request);
//                        results.add(bolean);
//                        gsonReport = gson.toJson(results);
//                        System.out.println(results + "\n" + request + bolean.toString());
//                        break;
//                    }
//                    case "/tag/unfollow": {
//                        System.out.println("un follow tag");
//                        Boolean bolean = TagFacade.unFollowTagFacade(request);
//                        results.add(bolean);
//                        gsonReport = gson.toJson(results);
//                        System.out.println(results + "\n" + request + bolean.toString());
//                        break;
//                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 3. response to client
    try {
        httpServletResponse.getWriter().println(gsonReport);
    } catch (IOException e) {
        e.printStackTrace();
        logger.error("Not convert to gson !");
    }
    logger.info("it time total: " + stopWatch.toString());
    return gsonReport;
}
}
