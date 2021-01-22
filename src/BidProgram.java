
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joshua Bastien Computer Science IA
 */
public class BidProgram extends javax.swing.JFrame {

    ArrayList<String> textdata;
    ArrayList<Integer> worth = new ArrayList<>();

    //Creates new form BidProgram and Auto Sorter to sort the rows 
    public BidProgram() {
        initComponents();

        jTable2.setAutoCreateRowSorter(true);
    }

    //Clears all text fields
    public void clearTF() {

        pnameTF.setText("");
        plengthTF.setText("");
        workersTF.setText("");
        labourTF.setText("");
        materialTF.setText("");
        profitTF.setText("");
        addcostsTF.setText("");
        concreteTF.setText("");
        ashphaltTF.setText("");
        competitorTF.setText("");

    }

    //Reads the text file and put each line of it into a String arraylist 
    public static ArrayList<String> readFile(String path) throws IOException {

        ArrayList<String> textdata;
        try (Scanner s = new Scanner(new File(path))) {
            textdata = new ArrayList<>();
            while (s.hasNext()) {
                textdata.add(s.next());
            }
        }
        return textdata;
    }

    //Puts text file data in table after some calculations
    public void updateTable() {

        try {
            //Reads data from text file
            String path = "C:\\Users\\remem\\Desktop\\bidinfo.txt";
            textdata = readFile(path);
            DefaultTableModel table = (DefaultTableModel) jTable2.getModel();

            //Deletes all table rows
            for (int i = table.getRowCount() - 1; i >= 0; i--) {
                table.removeRow(i);
            }

            //Cycles through number of projects
            for (int i = 0; i < (textdata.size() / 10); i++) {

                //The 8 variables for the 8 pieces of data needed per row
                String compPrice;
                String acRatio;
                String pname = textdata.get(i * 10);
                String plength = textdata.get(i * 10 + 1);
                String totalCost = Integer.toString(Integer.parseInt(textdata.get(i * 10 + 1)) * Integer.parseInt(textdata.get(i * 10 + 2)) * Integer.parseInt(textdata.get(i * 10 + 3)) + Integer.parseInt(textdata.get(i * 10 + 4)) + Integer.parseInt(textdata.get(i * 10 + 6)));
                String profit = textdata.get(i * 10 + 5);
                String bidPrice = Integer.toString((int) round(Double.parseDouble(totalCost) * (Double.parseDouble(profit) / 100) + Double.parseDouble(totalCost)));
                String worthStr = "Yes";
                String adjBid = "-1";

                //Checks if optional textfields were used and calulates all of the additional values if they were
                //worth ArrayList coincides with the number of projects, containing a 1 if project is worth it and 0 if not. Used later when deleting all unworthy projects
                worth.add(1);
                if (!textdata.get(i * 10 + 7).equals("-1") && !textdata.get(i * 10 + 8).equals("-1")) {
                    acRatio = Double.toString(round(Double.parseDouble(textdata.get(i * 10 + 7)) / Double.parseDouble(textdata.get(i * 10 + 8))));

                    if (Double.parseDouble(acRatio) < 1) {
                        worthStr = "No";
                        worth.set(i, 0);
                    }
                    
                } else {
                    acRatio = "-1";
                }
                if (!textdata.get(i * 10 + 9).equals("-1")) {
                    compPrice = textdata.get(i * 10 + 9);
                } else {
                    compPrice = "-1";
                }

                if (!"-1".equals(compPrice)) {

                    if (Double.parseDouble(totalCost) >= Double.parseDouble(compPrice)) {
                        adjBid = totalCost;
                        worthStr = "No";
                        worth.set(i, 0);
                    } else if (Double.parseDouble(bidPrice) >= Double.parseDouble(compPrice)) {
                        adjBid = Integer.toString(Integer.parseInt(compPrice) - 1);
                        worthStr = "No";
                        worth.set(i, 0);
                    } else {
                        adjBid = Integer.toString(Integer.parseInt(compPrice) - 1);
                    }
                }
                //Outputs previously calculated values in table 
                Object[] data = {pname, Integer.parseInt(plength), Integer.parseInt(totalCost), Integer.parseInt(profit), Double.parseDouble(acRatio), Integer.parseInt(bidPrice), Integer.parseInt(compPrice), Integer.parseInt(adjBid), worthStr};

                table.addRow(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(BidProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Rounds number to 2 decimal places
    public static double round(double numToRound) {
        numToRound = Math.round(numToRound * 100);
        return numToRound / 100;
    }

     //This method is called from within the constructor to initialize the form.
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        //Program will exit when 'x' is pressed
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //Initializes are form elements
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        submitB = new javax.swing.JButton();
        clearB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        pnameTF = new javax.swing.JTextField();
        plengthTF = new javax.swing.JTextField();
        workersTF = new javax.swing.JTextField();
        labourTF = new javax.swing.JTextField();
        materialTF = new javax.swing.JTextField();
        profitTF = new javax.swing.JTextField();
        concreteTF = new javax.swing.JTextField();
        ashphaltTF = new javax.swing.JTextField();
        competitorTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        addcostsTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        deleteRowB = new javax.swing.JButton();
        deleteNotWorthB = new javax.swing.JButton();
        deleteAllB = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        importB = new javax.swing.JButton();
        helpB = new javax.swing.JButton();
        defaultSB = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        //Fills all labels with their text
        jLabel1.setText("Project Location:");
        jLabel2.setText("Length of Project (days):");
        jLabel3.setText("Workers Needed:");
        jLabel4.setText("Labour Rate ($ per day per worker):");
        jLabel5.setText("Material Cost ($):");
        jLabel6.setText("Desired Profit Margin (%):");
        jLabel7.setFont(new java.awt.Font("sansserif", 2, 12)); 
        jLabel7.setText("Optional:");
        jLabel8.setText("Concrete Cost ($):");
        jLabel9.setText("Ashphalt Cost ($):");
        jLabel10.setText("Estimated Lowest Compteitors Bid ($):");
        jLabel11.setText("Additional Costs ($):");
        jLabel12.setFont(new java.awt.Font("sansserif", 0, 18)); 
        jLabel12.setText("Project List:");
        jLabel13.setText("Sort By:");
        jLabel14.setText("Note that a value of -1 means insufficient information was provided to do calculation.");
        jLabel15.setFont(new java.awt.Font("sansserif", 0, 18));
        jLabel15.setText("Project Details:");

        //Fills in button labels and what methods to execute when the button is pressed
        submitB.setText("Submit");
        submitB.addActionListener((java.awt.event.ActionEvent evt) -> {
            submitBActionPerformed(evt);
        });

        clearB.setText("Clear");
        clearB.addActionListener((java.awt.event.ActionEvent evt) -> {
            clearBActionPerformed(evt);
        });

        exitB.setText("Exit Program");
        exitB.addActionListener((java.awt.event.ActionEvent evt) -> {
            exitBActionPerformed(evt);
        });

        deleteRowB.setText("Delete Row");
        deleteRowB.addActionListener((java.awt.event.ActionEvent evt) -> {
            deleteRowBActionPerformed(evt);
        });
        
        deleteNotWorthB.setText("Delete Projects Not Worth Bidding On");
        deleteNotWorthB.addActionListener((java.awt.event.ActionEvent evt) -> {
            deleteNotWorthBActionPerformed(evt);
        });

        deleteAllB.setText("Delete All");
        deleteAllB.addActionListener((java.awt.event.ActionEvent evt) -> {
            deleteAllBActionPerformed(evt);
        });
        
        importB.setText("Import");
        importB.addActionListener((java.awt.event.ActionEvent evt) -> {
            importBActionPerformed(evt);
        });

        helpB.setText("Help!");
        helpB.addActionListener((java.awt.event.ActionEvent evt) -> {
            helpBActionPerformed(evt);
        });

        defaultSB.setText("Date Added");
        defaultSB.addActionListener((java.awt.event.ActionEvent evt) -> {
            defaultSBActionPerformed(evt);
        });

        //Sets up table
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                
            new Object [][] {},
            //Column titles here
            new String [] {
                "Location", "Length (days)", "Total Cost ($)", "Profit Margin (%)", "Concrete - Ashphalt Ratio", "Suggested Bid Price ($)", "Competitor's Pricing ($)", "Adjusted Bid Price ($)", "Worth Bidding On?"
            }
        ) {
            //Describes what data type each column will take, used to help sort the rows 
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //Quality of life formatting
        jTable2.setToolTipText("");
        jTable2.setAutoscrolls(false);
        jScrollPane2.setViewportView(jTable2);

        //Code that formats the locations of everything in the form
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(submitB)
                                        .addGap(18, 18, 18)
                                        .addComponent(clearB)
                                        .addGap(18, 18, 18)
                                        .addComponent(importB))
                                    .addComponent(jLabel15))
                                .addGap(95, 95, 95))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(106, 106, 106)))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(2, 2, 2)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel10))))
                                            .addGap(30, 30, 30)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(concreteTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(ashphaltTF)
                                                    .addComponent(competitorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(pnameTF)
                                                .addComponent(plengthTF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel3))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(addcostsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(labourTF)
                                                    .addComponent(materialTF)
                                                    .addComponent(profitTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(workersTF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(defaultSB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(deleteRowB)
                                .addGap(18, 18, 18)
                                .addComponent(deleteAllB)
                                .addGap(18, 18, 18)
                                .addComponent(deleteNotWorthB)
                                .addGap(404, 404, 404)
                                .addComponent(jLabel14))
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exitB)
                        .addGap(18, 18, 18)
                        .addComponent(helpB)))
                .addContainerGap(540, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(defaultSB)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(deleteRowB)
                                            .addComponent(deleteNotWorthB)
                                            .addComponent(deleteAllB)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(pnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(plengthTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(workersTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(labourTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(materialTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(profitTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(addcostsTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(concreteTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ashphaltTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6))
                            .addComponent(competitorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitB)
                            .addComponent(clearB)
                            .addComponent(importB))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitB)
                    .addComponent(helpB))
                .addGap(15, 15, 15))
        );
        pack();
    }                       
    
    //Submit button method
    private void submitBActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
        //Checks that all required textfields were filled in
        if ("".equals(pnameTF.getText()) || "".equals(plengthTF.getText()) || "".equals(workersTF.getText()) || "".equals(labourTF.getText())
                || "".equals(materialTF.getText()) || "".equals(profitTF.getText()) || "".equals(addcostsTF.getText())) {

            JOptionPane.showMessageDialog(null, "Please fill in all required information.");

        } else {
            
            //Inputs all textfields into text file bidinfo.txt
            try {
                FileWriter fw = new FileWriter("C:\\Users\\remem\\Desktop\\bidinfo.txt", true);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(pnameTF.getText());
                    bw.newLine();
                    bw.write(plengthTF.getText());
                    bw.newLine();
                    bw.write(workersTF.getText());
                    bw.newLine();
                    bw.write(labourTF.getText());
                    bw.newLine();
                    bw.write(materialTF.getText());
                    bw.newLine();
                    bw.write(profitTF.getText());
                    bw.newLine();
                    bw.write(addcostsTF.getText());
                    bw.newLine();
                    
                    //Checks if optional textfields are empty, if they are it gives them a value of -1
                    if (concreteTF.getText().equals("")) {
                        bw.write("-1");
                    } else {
                        bw.write(concreteTF.getText());
                    }
                    bw.newLine();
                    if (ashphaltTF.getText().equals("")) {
                        bw.write("-1");
                    } else {
                        bw.write(ashphaltTF.getText());
                    }
                    bw.newLine();
                    if (competitorTF.getText().equals("")) {
                        bw.write("-1");
                    } else {
                        bw.write(competitorTF.getText());
                    }
                    bw.newLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(BidProgram.class.getName()).log(Level.SEVERE, null, ex);
            }

            clearTF();
            updateTable();
        }
    }                                       

    //Exits the system upon 'Exit Program' button press
    private void exitBActionPerformed(java.awt.event.ActionEvent evt) {                                      
        System.exit(0);
    }                  
    
    //Clears text fields upon 'Clear' button press
    private void clearBActionPerformed(java.awt.event.ActionEvent evt) {                                       
        clearTF();
    }               
    
    //Delete row button
    private void deleteRowBActionPerformed(java.awt.event.ActionEvent evt) {                                           

        DefaultTableModel table = (DefaultTableModel) jTable2.getModel();
        
        int rowLocation = jTable2.getSelectedRow();
        
        //Checks user sorts by 'Date Added' first
        int confirm = JOptionPane.showConfirmDialog(null, "Projects must be sorted by 'Date Added' in order to delete row correctly, continue?", "Yes", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {

            //Gives this error if no row is selected
            if (rowLocation == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");

            } else {
            
                //Deletes row, then removes the projects info from the text file
                try {
                    FileWriter fw = new FileWriter("C:\\Users\\remem\\Desktop\\bidinfo.txt", false);
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        table.removeRow(rowLocation);
                        for (int i = 0; i < 10; i++) {
                            textdata.remove(rowLocation * 10);
                        }
                        for (int i = 0; i < textdata.size(); i++) {
                            bw.write(textdata.get(i));
                            bw.newLine();
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(BidProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }                                   

    //Delete all button method
    private void deleteAllBActionPerformed(java.awt.event.ActionEvent evt) {                                           

        DefaultTableModel table = (DefaultTableModel) jTable2.getModel();

        //Confirms rows are present and the user wants to delete them
        if (table.getRowCount() > 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all rows?", "Yes", JOptionPane.YES_NO_OPTION);
            
            if (confirm == 0) {
                FileWriter fw;
                try {
                    
                    //Removes all data from the textdata arraylist
                    fw = new FileWriter("C:\\Users\\remem\\Desktop\\bidinfo.txt", false);
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        int textdatasize = textdata.size();
                        for (int i = 0; i < textdatasize; i++) {
                            textdata.remove(0);
                        }
                        //Deletes all rows from table
                        int tableRowCount = table.getRowCount();
                        for (int i = 0; i < tableRowCount; i++) {
                            table.removeRow(0);
                        }
                        
                        //Rewrites bidinfo.txt to be empty
                        for (int i = 0; i < textdata.size(); i++) {
                            bw.write(textdata.get(i));
                            bw.newLine();
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(BidProgram.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }                  
    
    //Delete not worth method
    private void deleteNotWorthBActionPerformed(java.awt.event.ActionEvent evt) {                                                

        DefaultTableModel table = (DefaultTableModel) jTable2.getModel();
        FileWriter fw;

        
        //Checks user sorts by 'Date Added' first
        int confirm = JOptionPane.showConfirmDialog(null, "Projects must be sorted by 'Date Added' in order to delete row correctly, continue?", "Yes", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {

            try {
                fw = new FileWriter("C:\\Users\\remem\\Desktop\\bidinfo.txt", false);
                //Checks worth Arraylist to find all 0 values and deletes corresponding project from table and text file
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    //Checks worth Arraylist to find all 0 values and deletes corresponding project from table and text file
                    for (int i = 0; i < worth.size(); i++) {
                        if (worth.get(i).equals(0)) {
                            table.removeRow(i);
                            worth.remove(i);
                            for (int x = 0; x < 10; x++) {
                                textdata.remove(i * 10);
                            }
                            i = i - 1;
                        }
                    }
                    for (int i = 0; i < textdata.size(); i++) {
                        bw.write(textdata.get(i));
                        bw.newLine();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(BidProgram.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                            
    }
    
    //Updates the table
    private void importBActionPerformed(java.awt.event.ActionEvent evt) {               
        updateTable();
     }                                       

    private void helpBActionPerformed(java.awt.event.ActionEvent evt) {                                      
        //Outputs this helpful message when user clicks 'Help!' button
        JOptionPane.showMessageDialog(null, "This is a guide of some of the features and uses of this program. Each of the buttons will be described below. \n\nThe 'Import' button will import"
                + " any saved projects. So if the program was run, had 5 projects added to it, then closed, next time you run the program \nyou can retreive those 5 projects again by hitting import. Submitting "
                + "a new project will also do this, but in case the user doesn't want to have to \nsubmit a new project to view their current ones, they can hit 'Import'.\n\nThe 'Clear' button"
                + " will clear all the text fields.\n\nThe 'Submit' button will submit the inputted data and update the table after doing the necessary calculations. It is important that all data aside from \n"
                + "the first text field 'Project Location:' be inputted as integers, or else the program won't be able to properly process the data. The last 3 boxes are \noptional, meaning that the user can leave them blank if they do "
                + "not have that data available. \n\nThe 'Exit Program' button will exit the program, but don't worry, all of the projects in the table have been automatically saved and can be retrieved \nonce the program "
                + "is run again!\n\n Next, the table can be sorted by any one of it's column's simply by clicking on that column header, or by the date the project was added through the \n'Date Added' button.\n\n The 'Delete"
                + " Row' button will delete the selected row, but the data must be sorted by the data that it is added first.\n\n The 'Delete All' button will permanently delete all data.\n\nFinally, the 'Delete "
                + "Projects Not Worth Bidding On' button will delete all projects that the program has deemed not worth bidding on.");
    }                                     

    private void defaultSBActionPerformed(java.awt.event.ActionEvent evt) {                                          

        //Resets the Auto Sorter, which reverts the table back to its default state, where it lists the projects in order
        jTable2.setAutoCreateRowSorter(true);
    }                                
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {

        // Create and display the form 
        java.awt.EventQueue.invokeLater(() -> {
            new BidProgram().setVisible(true);
        });
    }

    // Variables declaration                 
    private javax.swing.JTextField addcostsTF;
    private javax.swing.JTextField ashphaltTF;
    private javax.swing.JButton clearB;
    private javax.swing.JTextField competitorTF;
    private javax.swing.JTextField concreteTF;
    private javax.swing.JButton defaultSB;
    private javax.swing.JButton deleteAllB;
    private javax.swing.JButton deleteNotWorthB;
    private javax.swing.JButton deleteRowB;
    private javax.swing.JButton exitB;
    private javax.swing.JButton helpB;
    private javax.swing.JButton importB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField labourTF;
    private javax.swing.JTextField materialTF;
    private javax.swing.JTextField plengthTF;
    private javax.swing.JTextField pnameTF;
    private javax.swing.JTextField profitTF;
    public javax.swing.JButton submitB;
    private javax.swing.JTextField workersTF;                
}