package com.wook;


import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.wook.service.GetGeoBlackList;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GetGeoBlackListTests {

    @Test
    public void test_no_quote() {

        String line = "1.1.1.1,1.1.1.2,AU,some description";
        String [] result = GetGeoBlackList.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.length, is(4));
        assertThat(result[0], is("1.1.1.1"));
        assertThat(result[1], is("1.1.1.2"));
        assertThat(result[2], is("AU"));
        assertThat(result[3], is("some description"));

    }

    @Test
    public void test_no_quote_but_double_quotes_in_column() throws Exception {

        String line = "1.1.1.1,1.1.1.2,AU,some \"\"description";

        String [] result = GetGeoBlackList.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.length, is(4));
        assertThat(result[0], is("1.1.1.1"));
        assertThat(result[1], is("1.1.1.2"));
        assertThat(result[2], is("AU"));
        assertThat(result[3], is("some description"));

    }

    @Test
    public void test_double_quotes() {

        String line = "\"1.1.1.1,1.1.1.2,AU,some description";
        
        String [] result = GetGeoBlackList.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.length, is(4));
        assertThat(result[0], is("1.1.1.1"));
        assertThat(result[1], is("1.1.1.2"));
        assertThat(result[2], is("AU"));
        assertThat(result[3], is("some description"));
    }

    @Test
    public void test_double_quotes_but_double_quotes_in_column() {

        String line = "\"1.1.1.1\",\"1.1.1.2\",\"AU\",\"some description\"";
        
        String [] result = GetGeoBlackList.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.length, is(4));
        assertThat(result[0], is("1.1.1.1"));
        assertThat(result[1], is("1.1.1.2"));
        assertThat(result[2], is("AU"));
        assertThat(result[3], is("some description"));

    }

    @Test
    public void test_double_quotes_but_comma_in_column() {

        String line = "\"1.1.1.1\",\"1.1,1.2\",\"AU\",\"some description\"";
        
        String [] result = GetGeoBlackList.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.length, is(4));
        assertThat(result[0], is("1.1.1.1"));
        assertThat(result[1], is("1.1,1.2"));
        assertThat(result[2], is("AU"));
        assertThat(result[3], is("some description"));
    }
}
