package com.learn.hbase;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * @author :lwy
 * @date 2018/7/10 14:18
 */
public class HbaseClientExample {

    public static void main(String[] args) throws IOException {
        new HbaseClientExample().connect();
    }

    private void connect() throws IOException {

        Configuration config = HBaseConfiguration.create();

        String path = this.getClass()
                .getClassLoader()
                .getResource("Hbase-site.xml")
                .getPath();

        config.addResource(new Path(path));

        try{
            HBaseAdmin.checkHBaseAvailable(config);
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
            return;
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //HBaseClientOperations HBaseClientOperations = new HBaseClientOperations();
        //HBaseClientOperations.run(config);

    }
}
