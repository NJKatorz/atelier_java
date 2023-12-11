package server;

import blacklist.BlacklistService;
import domaine.Query;
import domaine.QueryFactory;

import java.util.Scanner;

public class ProxyServer {

    private QueryFactory queryFactory;
    private BlacklistService blacklistService;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public ProxyServer(QueryFactory queryFactory, BlacklistService blacklistService) {
        this.queryFactory = queryFactory;
        this.blacklistService = blacklistService;
    }

    public void startServer() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // System.out.println("Entrez l'url : ");
            String url = scanner.nextLine();
            Query queryImpl = this.queryFactory.getQuery();
            queryImpl.setUrl(url);
            queryImpl.setMethod(Query.QueryMethod.GET);
            if (blacklistService.check(queryImpl)) {
                System.out.println("Query rejected : domain blacklised !");
                break;
            }else {
                QueryHandler queryHandler = new QueryHandler(queryImpl);
                queryHandler.start();
            }

        }
    }
}
