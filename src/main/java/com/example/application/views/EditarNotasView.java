package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.example.application.data.entity.Turma;
import com.example.application.data.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.awt.*;
import java.util.Collection;

@PageTitle("Editar Notas")
@Route(value = "editar-notas", layout = MainLayout.class)
@Uses(Icon.class)
public class EditarNotasView extends Composite<VerticalLayout> {

    public EditarNotasView() {

        //----------------------Componentes-----------------------

        H1 h1 = new H1("Notas");
        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button voltar = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

        IntegerField faltas = new IntegerField("Faltas");
        IntegerField nota1 = new IntegerField("Nota 1");
        IntegerField nota2 = new IntegerField("Nota 2");
        IntegerField nota3 = new IntegerField("Nota 3");
        IntegerField nota4 = new IntegerField("Nota 4");
        NumberField matricula = new NumberField("Matricula");
        Button editarNota = new Button("Confirmar");

        //----------------------Componentes Grade-----------------------

        Grid<Turma> turma = new Grid(Turma.class, false);
        setGridSampleData(turma);

        turma.addColumn(Turma::getMatricula).setHeader("Matricula");
        turma.addColumn(Turma::getNome).setHeader("Nome");
        turma.addColumn(Turma::getFaltas).setHeader("Faltas");
        turma.addColumn(Turma::getN1).setHeader("Nota1");
        turma.addColumn(Turma::getN2).setHeader("Nota2");
        turma.addColumn(Turma::getN3).setHeader("Nota3");
        turma.addColumn(Turma::getN4).setHeader("Nota4");

        //----------------------Alinhamentos-----------------------

        getContent().setHeightFull();
        getContent().setWidthFull();

        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h1);

        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);

        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setAlignItems(Alignment.START);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);

        //----------------------Botoes-----------------------

        editarNota.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        nota1.setWidth("6vw");
        nota1.setMin(0);
        nota1.setMax(10);
        nota1.setValue(0);

        nota2.setWidth("6vw");
        nota2.setMin(0);
        nota2.setMax(10);
        nota2.setValue(0);

        nota3.setWidth("6vw");
        nota3.setMin(0);
        nota3.setMax(10);
        nota3.setValue(0);

        nota4.setWidth("6vw");
        nota4.setMin(0);
        nota4.setMax(10);
        nota4.setValue(0);

        matricula.setMin(1);

        faltas.setWidth("6vw");
        faltas.setMin(0);
        faltas.setValue(0);

        voltar.addClickListener(e ->
                voltar.getUI().ifPresent(ui ->
                        ui.navigate("tela-principal"))
        );

        /*
        editarNota.addClickListener();
        */

        //-----------------------Adds-----------------------

        getContent().add(voltar);
        getContent().add(h1);
        getContent().add(turma);
        getContent().add(layoutRow);
        getContent().add(layoutRow2);

        layoutRow.add(matricula);
        layoutRow.add(faltas);
        layoutRow.add(nota1);
        layoutRow.add(nota2);
        layoutRow.add(nota3);
        layoutRow.add(nota4);

        layoutRow2.add(editarNota);
    }

    //-----------------------grade-----------------------

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private TurmaService samplePersonService;

    record SampleItem(String value, String label, Boolean disabled) {
    }

}
