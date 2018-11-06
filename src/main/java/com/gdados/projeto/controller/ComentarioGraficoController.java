/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdados.projeto.controller;

import com.gdados.projeto.facade.ComentarioFacade;
import com.gdados.projeto.model.Comentario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author PMBV-164029
 */
@Named
@SessionScoped
public class ComentarioGraficoController implements Serializable {

    @Inject
    private ComentarioFacade comentarioFacade;
    private List<Comentario> comentarios;

    private CartesianChartModel combinedModel;

    public ComentarioGraficoController() {
        comentarios = new ArrayList<>();
        createCombinedModelTeste();
    }

    public CartesianChartModel getCombinedModel() {
        return combinedModel;
    }

    public void teste() {
        comentarios = comentarioFacade.getAll();
//        createCombinedModel();
//        comentarios = new ArrayList<>();
//        for (Comentario c : comentarios) {
//            System.out.println(c.getDataRegistro());
//        }
    }

    private void createCombinedModel() {
        combinedModel = new BarChartModel();

        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Boys");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        LineChartSeries girls = new LineChartSeries();
        girls.setLabel("Girls");

        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        combinedModel.addSeries(boys);
        combinedModel.addSeries(girls);

        combinedModel.setTitle("Bar and Line");
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    public void createCombinedModelTeste() {
        combinedModel = new BarChartModel();

        BarChartSeries boys = new BarChartSeries();
        boys.setLabel("Comentários");

        comentarioFacade = new ComentarioFacade();
        comentarios = new ArrayList<>();
        comentarios = comentarioFacade.getAll();

        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

        Random random = new Random(1234);

        for (Comentario c : comentarios) {
            long quantidade = comentarioFacade.contaComentarioByData(c.getDataRegistro());
            boys.set(dt1.format(c.getDataRegistro()), quantidade);
        }

        combinedModel.addSeries(boys);

        combinedModel.setTitle("Quantidade de comentários por dia");
        combinedModel.setLegendPosition("ne");
        combinedModel.setMouseoverHighlight(false);
        combinedModel.setShowDatatip(false);
        combinedModel.setShowPointLabels(true);
        combinedModel.setAnimate(true);
        Axis yAxis = combinedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

}
