package de.thw.oofb.views;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import de.thw.oofb.models.Transaction;
import de.thw.oofb.repos.TransactionRepo;

@Route
public class MainView extends AbstractLoggedinView {

    private static final long serialVersionUID = 2616027330332669858L;

    @Override
    protected Component initView() {
        VerticalLayout content = new VerticalLayout();

        content.add(new Label("Transaktionen"));
        content.add(loadGrid());

        return content;
    }

    private Grid<Transaction> loadGrid() {
        Grid<Transaction> grid = new Grid<>(Transaction.class);

        grid.removeAllColumns();
        grid.addColumn(t -> t.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .setHeader("Datum/Uhrzeit").setSortable(true);
        grid.addColumn(t -> t.getPurpose().getName()).setHeader("Verwendungszweck").setSortable(true);
        grid.addColumn(t -> DecimalFormat.getCurrencyInstance(Locale.GERMANY).format(t.getAmount()))
                .setHeader("Betrag");

        TransactionRepo transactionRepo = new TransactionRepo();

        grid.setItems(transactionRepo.getTransactions());
        return grid;
    }
}