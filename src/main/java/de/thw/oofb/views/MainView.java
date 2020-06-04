package de.thw.oofb.views;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import de.thw.oofb.models.Transaction;
import de.thw.oofb.repos.TransactionRepo;

@Route
public class MainView extends AbstractLoggedinView {

    private static final long serialVersionUID = 2616027330332669858L;

    TransactionRepo transactionRepo;

    @Override
    protected Component initView() {
        this.transactionRepo = new TransactionRepo();

        VerticalLayout content = new VerticalLayout();

        double balance = transactionRepo.getTransactions().stream()
                .collect(Collectors.summarizingDouble(Transaction::getAmount)).getSum();

        content.add(new H3("Kontostand"));
        content.add(new Text(DecimalFormat.getCurrencyInstance(Locale.GERMANY).format(balance)));

        content.add(new H3("Transaktionen"));
        content.add(loadGrid());

        return content;
    }

    private Component loadGrid() {
        Grid<Transaction> grid = new Grid<>(Transaction.class);

        grid.removeAllColumns();
        grid.addColumn(t -> t.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .setHeader("Datum/Uhrzeit").setSortable(true);
        grid.addColumn(t -> t.getPurpose().getName()).setHeader("Verwendungszweck").setSortable(true);
        grid.addColumn(t -> DecimalFormat.getCurrencyInstance(Locale.GERMANY).format(t.getAmount()))
                .setHeader("Betrag");

        grid.setItems(transactionRepo.getTransactions());

        return grid;
    }
}