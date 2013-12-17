package com.ashishpaliwal.codekatta.elasticsearch;

import static org.elasticsearch.node.NodeBuilder.*;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.node.Node;

import java.util.Date;

/**
 *
 */
public class NodeClientTest {

  public static void main(String[] args) throws Exception {
    Node node = nodeBuilder().clusterName("flume").client(true).node();
    Client client = node.client();
    XContentBuilder builder = XContentFactory.jsonBuilder().startObject().
            field("user", "khimchy").field("postDate", new Date()).
            field("message", "elasticsearch hello world").endObject();
    String json = builder.string();
    IndexResponse indexResponse = client.prepareIndex("twitter", "tweet").
            setSource(json).execute().actionGet();
    System.out.println("index = "+indexResponse.getIndex());
    System.out.println("type = "+indexResponse.getType());
    System.out.println("id = "+indexResponse.getId());
    node.close();
  }

}
