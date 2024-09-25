package com.buil.api.request;

import lombok.Data;

@Data
public class UpdatePopulationRequest {
  private int total_inhabitants;
  private double growth;
  private double pbi;

  public UpdatePopulationRequest(int total_inhabitants, double growth, double pbi) {
    this.total_inhabitants = total_inhabitants;
    this.growth = growth;
    this.pbi = pbi;
  }
}