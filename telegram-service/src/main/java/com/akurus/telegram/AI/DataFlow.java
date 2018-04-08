package com.akurus.telegram.AI;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.speech.gcp.GcpAIConfiguration;
import ai.api.speech.gcp.GcpAIDataService;

import java.io.InputStream;

/**
 * Created by golizar on 24.12.17.
 */
public class DataFlow {
    private final AIDataService dataService;
    private final  GcpAIDataService dataServiceVoice;

    public DataFlow() {
        AIConfiguration configuration = new AIConfiguration("81c46bba0ec34d80a17c2b265ea7ccc8");
        GcpAIConfiguration configurationGSP = new GcpAIConfiguration("81c46bba0ec34d80a17c2b265ea7ccc8");
        this.dataServiceVoice = new GcpAIDataService(configurationGSP);
        this.dataService = new AIDataService(configuration);
    }

    public AIResponse request(String text) {

        AIRequest request = new AIRequest(text);
        AIResponse response = null;
        try {
            response = dataService.request(request);
        } catch (AIServiceException e) {
            e.printStackTrace();
        }

        return response;
    }

    public AIResponse request(InputStream voiceStream) {

        AIResponse response = null;
        try {
            response = dataServiceVoice.voiceRequest(voiceStream);
        } catch (AIServiceException e) {
            e.printStackTrace();
        }

        return response;
    }
}
