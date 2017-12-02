import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import javax.xml.transform.Result;
import java.io.IOException;

/**
 * Created by hasee on 2017/12/2.
 */
public class TestSolrJ {

    @Test
    public void testAddDocument() throws IOException, SolrServerException {
        SolrServer server = new HttpSolrServer("http://localhost:8090/solr/item");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test01");
        document.addField("item_title", "测试商品");
        document.addField("item_price",10086);
        server.add(document);
        server.commit();
    }
    @Test
    public void testdelDocument() throws IOException, SolrServerException {
        SolrServer server = new HttpSolrServer("http://localhost:8090/solr/item");
        server.deleteById("test01");
        server.commit();
    }
    @Test
    public void testQuery() throws SolrServerException {
        SolrServer server = new HttpSolrServer("http://localhost:8090/solr/item");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setStart(1);
        solrQuery.setRows(10);
        QueryResponse response = server.query(solrQuery);
        SolrDocumentList results = response.getResults();
        System.out.println(results);
        System.out.println(results.getMaxScore());
        for (SolrDocument document : results) {
            System.out.println(document.get("id"));
            System.out.println(document.get("item_title"));
        }

    }
}
