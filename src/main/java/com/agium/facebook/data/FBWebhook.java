package com.agium.facebook.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FBWebhook {
   public List<FBWebhookEntry> entry;
   public String object;
}
