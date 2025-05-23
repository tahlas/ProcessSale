package controller;

import integration.*;
import model.Amount;
import model.CashPayment;
import model.Sale;
import model.Receipt;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private final Printer printer;
    private final Register register;
    private final HandlerCreator handlerCreator;
    private Sale sale;

    /**
     * Creates a new instance.
     * @param printer The printer that is used to print the receipt.
     * @param register The register that is used by the cashier.
     * @param handlerCreator The handler creator that is used to create other handlers.
     */
    public Controller(Printer printer, Register register, HandlerCreator handlerCreator) {
        this.printer = printer;
        this.register = register;
        this.handlerCreator = handlerCreator;
    }

    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale(){
        sale = new Sale();
    }

    /**
     * Scans an item.
     */
    public void scanItem(String itemID){
        InventoryHandler inventoryHandler = handlerCreator.getInventoryHandler();
        ItemDTO scannedItem = inventoryHandler.getItemDTO(itemID);
        sale.addItem(scannedItem);
        register.presentCurrentScannedItem(sale, scannedItem);
    }

    /**
     * Ends the sale and processes the payment.
     * @param amountPaid The total price of the sale.
     */
    public void endSaleAndPay(Amount amountPaid){
        CashPayment payment = new CashPayment(amountPaid);
        sale.payForSale(payment);
        Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
        register.presentChangeToGiveToCustomer(sale);
        register.addPaymentToRegister(sale.totalCostAmount());
    }
}
