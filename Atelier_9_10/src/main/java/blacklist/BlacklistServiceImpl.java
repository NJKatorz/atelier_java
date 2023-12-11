package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class BlacklistServiceImpl implements BlacklistService {
    private static Set<String> blacklistedDomains;

    public BlacklistServiceImpl() {
    }

    static {
        try (FileInputStream in = new FileInputStream("blacklist.properties")) {
            Properties props = new Properties();
            props.load(in);
            blacklistedDomains = Set.of(props.getProperty("blacklistedDomains").split(";"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean check(Query query) {
        return blacklistedDomains.stream().anyMatch(domain -> query.getUrl().contains(domain));
    }

}
