package be.vinci.aj;

import blacklist.BlacklistService;
import blacklist.BlacklistServiceImpl;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import server.ProxyServer;

// https://duckduckgo.com/
// https://www.google.be/

public class Main {

    public static void main(String[] args) {
        /**
         * QueryFactory queryFactory = new QueryFactoryImpl();
         ProxyServer proxyServer = new ProxyServer(queryFactory);
         proxyServer.startServer();
         */
        QueryFactory queryFactory = new QueryFactoryImpl();
        BlacklistService blacklistService = new BlacklistServiceImpl();
        ProxyServer proxyServer = new ProxyServer(queryFactory, blacklistService);
        proxyServer.startServer();

    }
}