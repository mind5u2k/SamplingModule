package com.ison.app.model;

import java.util.ArrayList;
import java.util.List;

public class CallMixSamplingPer {

	// morning
	private double morningLongQ;
	private double morningLongR;
	private double morningLongC;

	private double morningMediumQ;
	private double morningMediumR;
	private double morningMediumC;

	private double morningShortQ;
	private double morningShortR;
	private double morningShortC;

	// Afternoon;
	private double afternoonLongQ;
	private double afternoonLongR;
	private double afternoonLongC;

	private double afternoonMediumQ;
	private double afternoonMediumR;
	private double afternoonMediumC;

	private double afternoonShortQ;
	private double afternoonShortR;
	private double afternoonShortC;

	// Evening
	private double eveningLongQ;
	private double eveningLongR;
	private double eveningLongC;

	private double eveningMediumQ;
	private double eveningMediumR;
	private double eveningMediumC;

	private double eveningShortQ;
	private double eveningShortR;
	private double eveningShortC;

	// Night
	private double nightLongQ;
	private double nightLongR;
	private double nightLongC;

	private double nightMediumQ;
	private double nightMediumR;
	private double nightMediumC;

	private double nightShortQ;
	private double nightShortR;
	private double nightShortC;

	public List<Double> getAllValues() {
		List<Double> values = new ArrayList<Double>();

		values.add(getMorningLongQ());
		values.add(getMorningLongR());
		values.add(getMorningLongC());
		values.add(getMorningMediumQ());
		values.add(getMorningMediumR());
		values.add(getMorningMediumC());
		values.add(getMorningShortQ());
		values.add(getMorningShortR());
		values.add(getMorningShortC());

		values.add(getAfternoonLongQ());
		values.add(getAfternoonLongR());
		values.add(getAfternoonLongC());
		values.add(getAfternoonMediumQ());
		values.add(getAfternoonMediumR());
		values.add(getAfternoonMediumC());
		values.add(getAfternoonShortQ());
		values.add(getAfternoonShortR());
		values.add(getAfternoonShortC());

		values.add(getEveningLongQ());
		values.add(getEveningLongR());
		values.add(getEveningLongC());
		values.add(getEveningMediumQ());
		values.add(getEveningMediumR());
		values.add(getEveningMediumC());
		values.add(getEveningShortQ());
		values.add(getEveningShortR());
		values.add(getEveningShortC());

		values.add(getNightLongQ());
		values.add(getNightLongR());
		values.add(getNightLongC());
		values.add(getNightMediumQ());
		values.add(getNightMediumR());
		values.add(getNightMediumC());
		values.add(getNightShortQ());
		values.add(getNightShortR());
		values.add(getNightShortC());

		return values;
	}

	public double getMorningLongQ() {
		return morningLongQ;
	}

	public void setMorningLongQ(double morningLongQ) {
		this.morningLongQ = morningLongQ;
	}

	public double getMorningLongR() {
		return morningLongR;
	}

	public void setMorningLongR(double morningLongR) {
		this.morningLongR = morningLongR;
	}

	public double getMorningLongC() {
		return morningLongC;
	}

	public void setMorningLongC(double morningLongC) {
		this.morningLongC = morningLongC;
	}

	public double getMorningMediumQ() {
		return morningMediumQ;
	}

	public void setMorningMediumQ(double morningMediumQ) {
		this.morningMediumQ = morningMediumQ;
	}

	public double getMorningMediumR() {
		return morningMediumR;
	}

	public void setMorningMediumR(double morningMediumR) {
		this.morningMediumR = morningMediumR;
	}

	public double getMorningMediumC() {
		return morningMediumC;
	}

	public void setMorningMediumC(double morningMediumC) {
		this.morningMediumC = morningMediumC;
	}

	public double getMorningShortQ() {
		return morningShortQ;
	}

	public void setMorningShortQ(double morningShortQ) {
		this.morningShortQ = morningShortQ;
	}

	public double getMorningShortR() {
		return morningShortR;
	}

	public void setMorningShortR(double morningShortR) {
		this.morningShortR = morningShortR;
	}

	public double getMorningShortC() {
		return morningShortC;
	}

	public void setMorningShortC(double morningShortC) {
		this.morningShortC = morningShortC;
	}

	public double getAfternoonLongQ() {
		return afternoonLongQ;
	}

	public void setAfternoonLongQ(double afternoonLongQ) {
		this.afternoonLongQ = afternoonLongQ;
	}

	public double getAfternoonLongR() {
		return afternoonLongR;
	}

	public void setAfternoonLongR(double afternoonLongR) {
		this.afternoonLongR = afternoonLongR;
	}

	public double getAfternoonLongC() {
		return afternoonLongC;
	}

	public void setAfternoonLongC(double afternoonLongC) {
		this.afternoonLongC = afternoonLongC;
	}

	public double getAfternoonMediumQ() {
		return afternoonMediumQ;
	}

	public void setAfternoonMediumQ(double afternoonMediumQ) {
		this.afternoonMediumQ = afternoonMediumQ;
	}

	public double getAfternoonMediumR() {
		return afternoonMediumR;
	}

	public void setAfternoonMediumR(double afternoonMediumR) {
		this.afternoonMediumR = afternoonMediumR;
	}

	public double getAfternoonMediumC() {
		return afternoonMediumC;
	}

	public void setAfternoonMediumC(double afternoonMediumC) {
		this.afternoonMediumC = afternoonMediumC;
	}

	public double getAfternoonShortQ() {
		return afternoonShortQ;
	}

	public void setAfternoonShortQ(double afternoonShortQ) {
		this.afternoonShortQ = afternoonShortQ;
	}

	public double getAfternoonShortR() {
		return afternoonShortR;
	}

	public void setAfternoonShortR(double afternoonShortR) {
		this.afternoonShortR = afternoonShortR;
	}

	public double getAfternoonShortC() {
		return afternoonShortC;
	}

	public void setAfternoonShortC(double afternoonShortC) {
		this.afternoonShortC = afternoonShortC;
	}

	public double getEveningLongQ() {
		return eveningLongQ;
	}

	public void setEveningLongQ(double eveningLongQ) {
		this.eveningLongQ = eveningLongQ;
	}

	public double getEveningLongR() {
		return eveningLongR;
	}

	public void setEveningLongR(double eveningLongR) {
		this.eveningLongR = eveningLongR;
	}

	public double getEveningLongC() {
		return eveningLongC;
	}

	public void setEveningLongC(double eveningLongC) {
		this.eveningLongC = eveningLongC;
	}

	public double getEveningMediumQ() {
		return eveningMediumQ;
	}

	public void setEveningMediumQ(double eveningMediumQ) {
		this.eveningMediumQ = eveningMediumQ;
	}

	public double getEveningMediumR() {
		return eveningMediumR;
	}

	public void setEveningMediumR(double eveningMediumR) {
		this.eveningMediumR = eveningMediumR;
	}

	public double getEveningMediumC() {
		return eveningMediumC;
	}

	public void setEveningMediumC(double eveningMediumC) {
		this.eveningMediumC = eveningMediumC;
	}

	public double getEveningShortQ() {
		return eveningShortQ;
	}

	public void setEveningShortQ(double eveningShortQ) {
		this.eveningShortQ = eveningShortQ;
	}

	public double getEveningShortR() {
		return eveningShortR;
	}

	public void setEveningShortR(double eveningShortR) {
		this.eveningShortR = eveningShortR;
	}

	public double getEveningShortC() {
		return eveningShortC;
	}

	public void setEveningShortC(double eveningShortC) {
		this.eveningShortC = eveningShortC;
	}

	public double getNightLongQ() {
		return nightLongQ;
	}

	public void setNightLongQ(double nightLongQ) {
		this.nightLongQ = nightLongQ;
	}

	public double getNightLongR() {
		return nightLongR;
	}

	public void setNightLongR(double nightLongR) {
		this.nightLongR = nightLongR;
	}

	public double getNightLongC() {
		return nightLongC;
	}

	public void setNightLongC(double nightLongC) {
		this.nightLongC = nightLongC;
	}

	public double getNightMediumQ() {
		return nightMediumQ;
	}

	public void setNightMediumQ(double nightMediumQ) {
		this.nightMediumQ = nightMediumQ;
	}

	public double getNightMediumR() {
		return nightMediumR;
	}

	public void setNightMediumR(double nightMediumR) {
		this.nightMediumR = nightMediumR;
	}

	public double getNightMediumC() {
		return nightMediumC;
	}

	public void setNightMediumC(double nightMediumC) {
		this.nightMediumC = nightMediumC;
	}

	public double getNightShortQ() {
		return nightShortQ;
	}

	public void setNightShortQ(double nightShortQ) {
		this.nightShortQ = nightShortQ;
	}

	public double getNightShortR() {
		return nightShortR;
	}

	public void setNightShortR(double nightShortR) {
		this.nightShortR = nightShortR;
	}

	public double getNightShortC() {
		return nightShortC;
	}

	public void setNightShortC(double nightShortC) {
		this.nightShortC = nightShortC;
	}

}
