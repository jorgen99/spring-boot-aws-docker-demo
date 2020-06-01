package com.jorgenlundberg.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FileIntegrationTests {

  @Value("${buildtime}")
  private String BUILD_TIME;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DynamoDb mockDb;

  @Test
  public void it_should_return_CREATED_on_success() throws Exception {
    doNothing().when(mockDb).put(any());
    mockMvc
        .perform(
            post("/event/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(anotherOne))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().json(""
            + "{"
            + "  \"status\": \"AWESOME\","
            + "  \"message\": \"File uploaded to s3: 's3://jorgenlundberg-cdk-file-event-bucket/some_file3.txt'\""
            + "}"
        ));
  }

  @Test
  public void it_should_return_OK_hello() throws Exception {
    mockMvc
        .perform(
            get("/")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(String.format("{ 'status':'AWESOME', 'message':'Hello World!', 'buildTime':'%s'}", BUILD_TIME)));
  }

  public static final String anotherOne = ""
      + "{\n"
      + "    \"awsRegion\": \"eu-north-1\",\n"
      + "    \"eventName\": \"ObjectCreated:Put\",\n"
      + "    \"eventSource\": \"aws:s3\",\n"
      + "    \"eventTime\": {\n"
      + "        \"iMillis\": 1590762542531,\n"
      + "        \"iChronology\": {\n"
      + "            \"iBase\": {\n"
      + "                \"iMinDaysInFirstWeek\": 4\n"
      + "            }\n"
      + "        }\n"
      + "    },\n"
      + "    \"eventVersion\": \"2.1\",\n"
      + "    \"requestParameters\": {\n"
      + "        \"sourceIPAddress\": \"5.150.247.51\"\n"
      + "    },\n"
      + "    \"responseElements\": {\n"
      + "        \"xAmzId2\": \"Z11QcHtg1YkkxCH5sHhn1kLwcBbMwNly6NDUyRKNpfLcQILV3kw5eupEhUuG3+BiEAUG+WVslLI11ADydgIXkxDNLD8RSq/j4U3EBmeNIAM=\",\n"
      + "        \"xAmzRequestId\": \"347B0765AEFF5009\"\n"
      + "    },\n"
      + "    \"s3\": {\n"
      + "        \"configurationId\": \"ODJhMzY5ZDItMDdmZi00MzY2LTkwYTItMGEzNjk3ZWQ0Yjll\",\n"
      + "        \"bucket\": {\n"
      + "            \"name\": \"jorgenlundberg-cdk-file-event-bucket\",\n"
      + "            \"ownerIdentity\": {\n"
      + "                \"principalId\": \"A2EHEBS2ZH7OYT\"\n"
      + "            },\n"
      + "            \"arn\": \"arn:aws:s3:::jorgenlundberg-cdk-file-event-bucket\"\n"
      + "        },\n"
      + "        \"object\": {\n"
      + "            \"key\": \"some_file3.txt\",\n"
      + "            \"size\": 0,\n"
      + "            \"eTag\": \"d41d8cd98f00b204e9800998ecf8427e\",\n"
      + "            \"versionId\": \"\",\n"
      + "            \"sequencer\": \"005ED11C2F4161DA47\"\n"
      + "        },\n"
      + "        \"s3SchemaVersion\": \"1.0\"\n"
      + "    },\n"
      + "    \"userIdentity\": {\n"
      + "        \"principalId\": \"AWS:AIDAXY7LZRH7M722H7OFM\"\n"
      + "    }\n"
      + "}";
}
