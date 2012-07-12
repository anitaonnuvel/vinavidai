package org.ahp.vv.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import org.ahp.commons.util.AhpResourceUtil;
import org.ahp.core.pojo.User;
import org.ahp.test.AhpSpringBaseTest;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.xml.sax.InputSource;

public class TestLoginDAO extends AhpSpringBaseTest {
    
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat( "yyyy-mm-dd hh:mm:ss.S" );
    
    @Test
    public void testLoadUserByLoginName() throws Exception {
        java.sql.Timestamp lTimestamp = new java.sql.Timestamp( System.currentTimeMillis() );        
        Connection lConnection = DataSourceUtils.getConnection( super.getDataSource() );
        IDatabaseConnection lIDatabaseConnection = new DatabaseConnection( lConnection, "VINAVIDAI" );
        InputSource lInputSource = new InputSource( AhpResourceUtil.getClassPathResource( "classpath:dbunit/test-dbUnit.xml" ) );
        /*System.out.println( lInputSource );
        ReplacementDataSet lDataSet = new ReplacementDataSet( new FlatXmlDataSet( new FlatXmlProducer( lInputSource ) ) );
        lDataSet.addReplacementObject( "[SYSDATE]", DATE_FORMATTER.format( lTimestamp ) );
        lDataSet.addReplacementObject( "[NULL]", null );
        try {
            DatabaseOperation.REFRESH.execute( lIDatabaseConnection, lDataSet );
            lConnection.commit();
        } finally {
            DataSourceUtils.releaseConnection( lConnection, super.getDataSource() );
        }*/
        User lUser = mLoginDAO.loadUserByLoginName( "USER1" );
        System.out.println( lUser.getLoginName() );
    }

}
