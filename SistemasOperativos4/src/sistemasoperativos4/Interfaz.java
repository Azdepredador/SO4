package sistemasoperativos4;

/*

 Copyright (c) 2017 Javier Rubio

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.



 */
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class Interfaz extends javax.swing.JFrame {

    int numeroProcesos = 0;
    int numeroProcesosM = 0;
    int lotesPen = 0;
    int contador = 1;
    int tiempo = 0;
    int mostrarLotes = 1;
    int lote = 0;
    boolean keyP = false, keyE = false, keyC = false, keyI = false, keyT = false, keyN = false;
    int tiempoRespuesta = 1;
    int id = 0;

    private final ArrayList<Procesos> procesos = new ArrayList<>();
    private final ArrayList<Bloqueados> bloqueados = new ArrayList<>();
    private final ArrayList<Procesos> listos = new ArrayList<>();
    private final ArrayList<BCP> tabla = new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        mostrarDatos();

        tablaDatos.setVisible(false);

    }

    public void meterDatosAlaTabla() {
        //espera=servicio-restante
        
        int esperaAux;
        String servicio,restante;
        
        /*String id,String op,String res,String ttl,String tf,
         String ret,String ser,String esp,String resp, String estado, String rest*/
        for(int i=0; i<listos.size(); i++){
                    datosEnTabla(listos.get(i).getId(),
                    "",
                    "", "",
                    "", "", "", "",
                    "", "", "");
        }

        for (int i = 0; i < procesos.size(); i++) {
            
            servicio=procesos.get(i).getTiempoTranscurrido();
            restante=procesos.get(i).getTiempoMaximo();
            
            esperaAux=Integer.parseInt(restante)-Integer.parseInt(servicio);
            
            if(esperaAux <0){
                esperaAux=Integer.parseInt(servicio)-Integer.parseInt(restante);
            }
            
            
            datosEnTabla(procesos.get(i).getId(),
                    procesos.get(i).getOp1() + procesos.get(i).getOperators() + procesos.get(i).getOp2(),
                    "", procesos.get(i).getTiempoLlegada(),
                    "", "", procesos.get(i).getTiempoTranscurrido(), Integer.toString(esperaAux),
                    procesos.get(i).getTiempoRespuesta(), "", procesos.get(i).getTiempoMaximo());
            
         }
        for (int i = 0; i < bloqueados.size(); i++) {
            
            servicio=bloqueados.get(i).getTiempoTranscurrido();
            restante=bloqueados.get(i).getTiempoMaximo();
            
            esperaAux=Integer.parseInt(restante)-Integer.parseInt(servicio);
            
            if(esperaAux <0){
                esperaAux=Integer.parseInt(servicio)-Integer.parseInt(restante);
            }
            
            
            datosEnTabla(bloqueados.get(i).getId(),
                    bloqueados.get(i).getOp1() + "+" + bloqueados.get(i).getOp2(),
                    "", bloqueados.get(i).getTiempoLlegada(),
                    "", "", bloqueados.get(i).getTiempoTranscurrido(), Integer.toString(esperaAux), // espera
                    bloqueados.get(i).getTiempoRespuesta(), bloqueados.get(i).getTiempoMaximo(), bloqueados.get(i).getTiempoMaximo());

        }
        for (int i = 0; i < tabla.size(); i++) {
            datosEnTabla(tabla.get(i).getId(),
                    tabla.get(i).getOp(),
                    tabla.get(i).getRes(),
                    tabla.get(i).getTiempoLlegada(),
                    tabla.get(i).getTiempoFinalizacion(),
                    tabla.get(i).getTiempoRetorno(),
                    tabla.get(i).getTiempoServicio(),
                    tabla.get(i).getTiempoEspera(), // espera
                    tabla.get(i).getTiempoRespues(),
                    tabla.get(i).getEstado(),
                    tabla.get(i).getTiempoRestante());

        }
    }

    public void procesosTerminados(String id, String estado, String op, String res,
            String tll, String tf, String tr, String te, String ts, String tres,
            String trespuesta) {

        tabla.add(new BCP(id, estado, op, res, tll, tf, tr, te, ts, tres, trespuesta));

    }

    public void mostrarDatos() {
        modelo.addColumn("Id");
        modelo.addColumn("Estado");
        modelo.addColumn("Op");
        modelo.addColumn("Resultado");
        modelo.addColumn("TLL");
        modelo.addColumn("TF");
        modelo.addColumn("TRetorno");
        modelo.addColumn("TServicio");
        modelo.addColumn("TEspera");
        modelo.addColumn("TRespuesta");
        modelo.addColumn("TRestante");
        tablaDatos.setModel(modelo);
    }

    public void datosEnTabla(String id, String op, String res, String ttl, String tf,
            String ret, String ser, String esp, String resp, String estado, String rest) {
        String[] datos = new String[11];

        datos[0] = id;
        datos[1] = estado;
        datos[2] = op;
        datos[3] = res;
        datos[4] = ttl;
        datos[5] = tf;
        datos[6] = ret;
        datos[7] = ser;
        datos[8] = esp;
        datos[9] = resp;
        datos[10] = rest;

        modelo.addRow(datos);
        tablaDatos.setModel(modelo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        ejecutarB = new javax.swing.JButton();
        registrarB = new javax.swing.JButton();
        contadorGlobal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lotesEjecucionT = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        procesoEjecucion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        procesoTerminadoL = new javax.swing.JTextArea();
        contadorL = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tiempoTranscurridoL = new javax.swing.JLabel();
        tiempoRestanteL = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        numeroProcesosT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        procesosL = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bloqueadosT = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        bloqueadosL = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistemas Operativos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        ejecutarB.setText("Ejecutar");
        ejecutarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarBActionPerformed(evt);
            }
        });
        ejecutarB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ejecutarBKeyPressed(evt);
            }
        });

        registrarB.setText("Registrar");
        registrarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBActionPerformed(evt);
            }
        });

        contadorGlobal.setText("Contador Global: ");

        jLabel1.setText("Procesos listos");

        lotesEjecucionT.setEditable(false);
        lotesEjecucionT.setColumns(20);
        lotesEjecucionT.setRows(5);
        jScrollPane1.setViewportView(lotesEjecucionT);

        jLabel4.setText("Proceso en ejecución");

        procesoEjecucion.setEditable(false);
        procesoEjecucion.setColumns(20);
        procesoEjecucion.setRows(5);
        jScrollPane2.setViewportView(procesoEjecucion);

        jLabel6.setText("Procesos Terminados");

        procesoTerminadoL.setEditable(false);
        procesoTerminadoL.setColumns(20);
        procesoTerminadoL.setRows(5);
        jScrollPane3.setViewportView(procesoTerminadoL);

        contadorL.setText("0");

        jLabel7.setText("Tiempo transcurrido:");

        jLabel8.setText("Tiempo restante:");

        tiempoTranscurridoL.setText("0");

        tiempoRestanteL.setText("0");

        jLabel10.setText("Numero de Procesos: ");

        jLabel5.setText("Procesos en Espera");

        procesosL.setText("0");

        jLabel11.setText("Bloqueados");

        bloqueadosT.setColumns(20);
        bloqueadosT.setRows(5);
        jScrollPane4.setViewportView(bloqueadosT);

        jLabel2.setText("Contador bloqueados: ");

        bloqueadosL.setText("0");

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id"
            }
        ));
        jScrollPane5.setViewportView(tablaDatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addGap(108, 108, 108))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(numeroProcesosT, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(registrarB, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(ejecutarB))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(procesosL)
                                        .addGap(44, 44, 44)
                                        .addComponent(contadorGlobal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(contadorL))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bloqueadosL)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tiempoTranscurridoL))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(tiempoRestanteL)))
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(numeroProcesosT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registrarB)
                            .addComponent(ejecutarB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(contadorGlobal)
                                .addComponent(contadorL))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(procesosL)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(bloqueadosL))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tiempoTranscurridoL))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tiempoRestanteL))
                        .addGap(13, 13, 13)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ejecutarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarBActionPerformed
        // TODO add your handling code here:
        tablaDatos.setVisible(false);
        modelo.getDataVector().removeAllElements();
        numeroProcesosM -= 4;
        procesosL.setText(Integer.toString(numeroProcesosM));

        procesoTerminadoL.setText("");
        int numProces = numeroProcesos;

        lotesPen = (numProces / 4);
        mostrarLotes = 1;
        contador = 0;
        lotesEjecucionT.setText("");

        if (numProces % 4 == 0) {

            lotesPen = lotesPen - 1;
        }

        mostrarLotesEjecucion();

        try {
            simularContador();
            // task.isDone();
        } catch (InterruptedException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_ejecutarBActionPerformed

    public void mostrarLotesEjecucion() {

        lotesEjecucionT.setText("");

        for (int j = 0; j < procesos.size(); j++) {

            if (j == 4) {
                break;
            }

            lotesEjecucionT.append("ID: " + procesos.get(j).getId() + "    "
                    + "TR: " + procesos.get(j).getTiempoMaximo() + "     "
                    + "TME: " + procesos.get(j).getTiempoMaximoEstimado() + "\n");

            if (procesos.get(j).getTiempoLlegada().equals("0")) {
                procesos.get(j).setTiempoLlegada(Integer.toString(contador));
            }

            if (procesos.get(j).getId().equals("0")) {
                procesos.get(j).setTiempoLlegada("0");
            } else if (procesos.get(j).getId().equals("1")) {
                procesos.get(j).setTiempoLlegada("0");
            } else if (procesos.get(j).getId().equals("2")) {
                procesos.get(j).setTiempoLlegada("0");
            } else if (procesos.get(j).getId().equals("3")) {
                procesos.get(j).setTiempoLlegada("0");
            }

        }

    }

    public void registrarUnoNuevo() {
        int op1, op2, op, tiempoMaximo, numLote = 1;
        id++;
        boolean aux = false;
        String[] operadores = {"+", "/", "*", "%", "+", "sqrt", "-"}; //potencia
        op1 = (int) (Math.random() * 25 + 1);
        op = (int) (Math.random() * 2 + 0);
        op2 = (int) (Math.random() * 25 + 1);
        tiempoMaximo = (int) (Math.random() * 20 + 10);

        listos.add(new Procesos("",
                Integer.toString(op1),
                operadores[op],
                Integer.toString(op2),
                "",
                Integer.toString(id),
                Integer.toString(tiempoMaximo),
                Integer.toString(numLote), "0", "", "", "", "", " ", "0",
                Integer.toString(tiempoMaximo)
        ));

    }

    @SuppressWarnings("empty-statement")
    private void registrarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBActionPerformed
        // TODO add your handling code here:
        procesos.clear();
        numeroProcesos = Integer.parseInt(numeroProcesosT.getText());
        int op1, op2, op, tiempoMaximo, numLote = 1;

        boolean aux = false;
        String[] operadores = {"+", "/", "*", "%", "+", "sqrt", "-"}; //potencia

        lote = numeroProcesos / 4;

        if (numeroProcesos % 4 != 0) {
            lote++;
        }
        numeroProcesosM = numeroProcesos;

        procesosL.setText(Integer.toString(numeroProcesosM));

        for (int i = 1; i <= numeroProcesos; i++) {

            op1 = (int) (Math.random() * 25 + 1);
            op = (int) (Math.random() * 2 + 0);
            op2 = (int) (Math.random() * 25 + 1);
            tiempoMaximo = (int) (Math.random() * 20 + 10);

            if (aux) {
                numLote++;
                aux = false;
            }

            if (i % 4 == 0) {

                aux = true;
            }
            if (i >= 5) {

                listos.add(new Procesos("",
                        Integer.toString(op1),
                        operadores[op],
                        Integer.toString(op2),
                        "",
                        Integer.toString(id),
                        Integer.toString(tiempoMaximo),
                        Integer.toString(numLote), "0", "", "", "", "", " ", "0",
                        Integer.toString(tiempoMaximo)
                ));

            } else {

                procesos.add(new Procesos("",
                        Integer.toString(op1),
                        operadores[op],
                        Integer.toString(op2),
                        "",
                        Integer.toString(id),
                        Integer.toString(tiempoMaximo),
                        Integer.toString(numLote), "0", "", "", "", "", " ", "0",
                        Integer.toString(tiempoMaximo)
                ));

            }

            id++;
        }

        numeroProcesosT.setText("");


    }//GEN-LAST:event_registrarBActionPerformed

    public void aListos() {

        String op1P, operatorsP, op2P, resultP, idP, tiempoMaximoP, loteP, tiempoTranscurridoP,
                tiempoLlegadaP, tiempoRespuestaP, tiempoEsperaP, tiempoMaximoEstimadoP;

        if (!listos.isEmpty()) {

            op1P = listos.get(0).getOp1();
            operatorsP = listos.get(0).getOperators();
            op2P = listos.get(0).getOp2();
            resultP = listos.get(0).getResult();
            idP = listos.get(0).getId();
            tiempoMaximoP = listos.get(0).getTiempoMaximo();
            loteP = listos.get(0).getLote();
            tiempoTranscurridoP = listos.get(0).getTiempoTranscurrido();
            tiempoLlegadaP = listos.get(0).getTiempoLlegada();
            tiempoRespuestaP = listos.get(0).getTiempoRespuesta();
            tiempoEsperaP = listos.get(0).getTiempoEspera();
            tiempoMaximoEstimadoP = listos.get(0).getTiempoMaximoEstimado();

            procesos.add(new Procesos("", op1P, operatorsP, op2P,
                    resultP, idP, tiempoMaximoP, loteP, tiempoTranscurridoP, "", tiempoEsperaP, "", "", tiempoRespuestaP, tiempoLlegadaP,
                    tiempoMaximoEstimadoP));

            listos.remove(0);

        }

    }

    public boolean verificaID(String id) {
        for (int i = 0; i < numeroProcesos; i++) {

            if (procesos.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void ejecutarBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ejecutarBKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_E:
                if (!keyP || !keyT) {
                    keyE = true;
                }

                break;
            case KeyEvent.VK_P:
                keyP = true;
                break;
            case KeyEvent.VK_C:
                keyC = true;
                tablaDatos.setVisible(false);
                modelo.getDataVector().removeAllElements();
                synchronized (this) {
                    notifyAll();
                }
                break;
            case KeyEvent.VK_I:

                if (!keyP || !keyT) {
                    keyI = true;
                }

                break;
            case KeyEvent.VK_T:
                keyT = true;

                break;
            case KeyEvent.VK_N:

                if (!keyP || !keyT) {

                    keyN = true;

                }

                break;

        }


    }//GEN-LAST:event_ejecutarBKeyPressed

    public void simularContador() throws InterruptedException {
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                try {
                    proceso();
                    //To change body of generated methods, choose Tools | Templates.
                } catch (InterruptedException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    public String operacion(int aux) {
        String signo = procesos.get(aux).getOperators();
        int op1 = Integer.parseInt(procesos.get(aux).getOp1());
        int op2 = Integer.parseInt(procesos.get(aux).getOp2());
        int res;

        if (signo.equals("+")) {
            res = op1 + op2;
        } else if (signo.equals("-")) {
            res = op1 - op2;

        } else if (signo.equals("*")) {
            res = op1 * op2;
        } else if (signo.equals("/")) {
            res = op1 / op2;
        } else if (signo.equals("%")) {
            res = op1 % op2;
        } else if (signo.equals("potencia")) {
            res = (int) Math.pow(op1, op2);
        } else if (signo.equals("sqrt")) {
            res = (int) Math.sqrt(op2);
        } else {
            res = 0;
        }
        return Integer.toString(res);
    }

    public void mostrarBloqueados() {
        bloqueadosT.setText("");
        for (int i = 0; i < bloqueados.size(); i++) {

            bloqueadosT.append("ID: " + bloqueados.get(i).getId() + "    "
                    + "TR: " + bloqueados.get(i).getTiempoMaximo() + "    "
                    + "TME: " + bloqueados.get(i).getTiempoMaximoEstimado() + "\n");
        }
    }

    public void BloqueadoListo() {
        String op1P, operatorsP, op2P, resultP, idP, tiempoMaximoP, loteP, tiempoTranscurridoP,
                tiempoLlegadaP, tiempoRespuestaP, tiempoEsperaP, tiempoMaximoEstimadoP;
        int tiempoMaximoI;

        if (bloqueados.isEmpty()) {
            System.out.println("Error bloqueados vacio");
        } else {

            op1P = bloqueados.get(0).getOp1();
            operatorsP = bloqueados.get(0).getOperators();
            op2P = bloqueados.get(0).getOp2();
            resultP = bloqueados.get(0).getResult();
            idP = bloqueados.get(0).getId();

            tiempoMaximoI = Integer.parseInt(bloqueados.get(0).getTiempoMaximo());

            tiempoMaximoP = Integer.toString(tiempoMaximoI - 1);
            loteP = bloqueados.get(0).getLote();
            tiempoTranscurridoP = bloqueados.get(0).getTiempoTranscurrido();
            tiempoLlegadaP = bloqueados.get(0).getTiempoLlegada();
            tiempoRespuestaP = bloqueados.get(0).getTiempoRespuesta();
            tiempoEsperaP = bloqueados.get(0).getTiempoEspera();
            tiempoMaximoEstimadoP = bloqueados.get(0).getTiempoMaximoEstimado();

            procesos.add(new Procesos("", op1P, operatorsP, op2P,
                    resultP, idP, tiempoMaximoP, loteP, tiempoTranscurridoP, "", tiempoEsperaP, "", "",
                    tiempoRespuestaP, tiempoLlegadaP, tiempoMaximoEstimadoP));

            bloqueados.remove(0);

        }

    }

    public void proceso() throws InterruptedException {
        boolean aumenta = false;
        String op1P, operatorsP, op2P, resultP, idP, tiempoMaximoP, loteP,
                tiempoTranscurridoP, tiempoLlegadaP, tiempoRespuestaP, tiempoEsperaP,
                tiempoMaximoEstimadoP;
        int tiempoRetorno, tf, tll;
        int i = 0;
        int c = 1;
        int tr, ts, te;

        for (int k = 0; k < lote; k++) {

            do {

                mostrarBloqueados();
                mostrarLotesEjecucion();

                i = 0;

                if (procesos.isEmpty()) {
                    //System.out.println("Vacio");
                    if (!bloqueados.isEmpty()) {
                        procesoEjecucion.setText("");
                        //  System.out.println("Bloqueado vacios");
                        while (true) {

                            //System.out.println("Holo");
                            contadorL.setText(Integer.toString(contador + 1));
                            contador++;

                            if (keyT) {
                                meterDatosAlaTabla();
                                tablaDatos.setVisible(true);

                                synchronized (this) {
                                    wait();
                                }
                                keyT = false;

                            }

                            Thread.sleep(500);
                            bloqueadosL.setText(Integer.toString(c));
                            if (c == 8 || c > 8) {
                                //bloqueadosL.setText(Integer.toString(c));
                                c = 1;
                                BloqueadoListo();
                                mostrarBloqueados();
                                break;
                            }
                            c++;

                        }
                    } else {
                        break;
                    }
                }
                // procesos.get(i).setTiempoMaximoEstimado(procesos.get(i).getTiempoMaximo());
                procesos.get(i).setResult(operacion(i)); //resultado operacion

                if (procesos.get(i).getTiempoRespuesta().equals(" ")) {

                    if (procesos.get(i).getId().equals("0")) {
                        procesos.get(i).setTiempoRespuesta("0");
                    } else {
                        int TLL = Integer.parseInt(procesos.get(i).getTiempoLlegada());
                        int res = tiempoRespuesta - TLL;
                        res -= 1;

                        if (res < 0) {

                            res = TLL - tiempoRespuesta;
                            res -= 1;
                        }

                    //menos 1
                        procesos.get(i).setTiempoRespuesta(Integer.toString(res));
                    }

                }

                procesoEjecucion.setText(
                        "Operacion: " + procesos.get(i).getOp1() + " " + procesos.get(i).getOperators() + " " + procesos.get(i).getOp2() + "\n"
                        + "Tiempo maximo estimado: " + procesos.get(i).getTiempoMaximoEstimado() + "\n"
                        + "id: " + procesos.get(i).getId());

                tiempo = Integer.parseInt(procesos.get(i).getTiempoMaximo());

                tiempoTranscurridoL.setText("0");
                String op = procesos.get(i).getOp1() + " " + procesos.get(i).getOperators() + " " + procesos.get(i).getOp2();

                int j;
                for (j = 0; j < tiempo; j++) {

                    tiempoRespuesta++;
                    tiempoTranscurridoL.setText(Integer.toString(j));
                    tiempoRestanteL.setText(Integer.toString(tiempo - j));

                    contadorL.setText(Integer.toString(contador + 1));

                    procesos.get(i).setTiempoTranscurrido(Integer.toString(j));
                    procesos.get(i).setTiempoMaximo(Integer.toString(tiempo - j));
                    mostrarLotesEjecucion();

                    Thread.sleep(500);

                    bloqueadosL.setText(Integer.toString(c));

                    if (aumenta) {
                        c++;
                    }

                    if (c == 9) {
                         //bloqueadosL.setText(Integer.toString(c));

                        c = 1;
                        BloqueadoListo();
                        mostrarBloqueados();

                    }

                    if (keyP) {
                        // tablaDatos.setVisible(true);
                        synchronized (this) {
                            wait();
                        }
                        keyP = false;

                    }
                    if (keyT) {
                        meterDatosAlaTabla();
                        tablaDatos.setVisible(true);

                        synchronized (this) {
                            wait();
                        }
                        keyT = false;

                    }
                    if (keyE) {

                        procesos.get(i).setResult("Error");
                        procesos.get(i).setTiempoTranscurrido(Integer.toString(j));
                        // mostrarLotesEjecucion();

                        op = "Error";

                        k--;
                        break;
                    }
                    if (keyI) {

                        op1P = procesos.get(i).getOp1();
                        operatorsP = procesos.get(i).getOperators();
                        op2P = procesos.get(i).getOp2();
                        resultP = procesos.get(i).getResult();
                        idP = procesos.get(i).getId();
                        tiempoMaximoP = procesos.get(i).getTiempoMaximo();
                        loteP = procesos.get(i).getLote();
                        tiempoTranscurridoP = procesos.get(i).getTiempoTranscurrido();
                        tiempoLlegadaP = procesos.get(i).getTiempoLlegada();
                        tiempoRespuestaP = procesos.get(i).getTiempoRespuesta();
                        tiempoEsperaP = procesos.get(i).getTiempoEspera();
                        tiempoMaximoEstimadoP = procesos.get(i).getTiempoMaximoEstimado();

                        bloqueados.add(new Bloqueados("", op1P, operatorsP, op2P,
                                resultP, idP, tiempoMaximoP, loteP, tiempoTranscurridoP, "", tiempoEsperaP, "", "", tiempoRespuestaP, tiempoLlegadaP,
                                tiempoMaximoEstimadoP));//falta tiempo de llegada es el ultimo

                        procesos.remove(i);

                        k--;
                        aumenta = true;

                        break;

                    }
                    if (keyN) {
                        registrarUnoNuevo();
                        numeroProcesosM += 1;
                        procesosL.setText(Integer.toString(numeroProcesosM));
                        keyN = false;
                    }

                    contador++;

                }

                if (keyI) {
                    keyI = false;

                    break;
                }
                if (keyE) {
                    keyE = false;
                } else {
                    procesos.get(i).setTiempoTranscurrido(Integer.toString(j));
                }

                procesos.get(i).setTiempoFinalizacion(Integer.toString(contador));
                tf = Integer.parseInt(procesos.get(i).getTiempoFinalizacion());
                tll = Integer.parseInt(procesos.get(i).getTiempoLlegada());
                tiempoRetorno = tf - tll;
                procesos.get(i).setTiempoRetorno(Integer.toString(tiempoRetorno));

                ts = Integer.parseInt(procesos.get(i).getTiempoTranscurrido());
                tr = Integer.parseInt(procesos.get(i).getTiempoRetorno());
                te = (tr - ts);
                te -= 1;
//menos 1
                if (te <= 0) {

                    procesos.get(i).setTiempoEspera("0");

                } else {
                    procesos.get(i).setTiempoEspera(Integer.toString(te + 1));
                }

                procesoTerminadoL.append(
                        "id: " + procesos.get(i).getId() + "\t"
                        // + "lote: " + procesos.get(i).getLote() + "    "
                        + "Op: " + op + "\t"
                        + "resultado: " + procesos.get(i).getResult() + "\t"
                        + "TLL: " + procesos.get(i).getTiempoLlegada() + "\t"
                        + "TF: " + procesos.get(i).getTiempoFinalizacion() + "\t"
                        + "TRetorno: " + procesos.get(i).getTiempoRetorno() + "\t"
                        + "TServicio: " + procesos.get(i).getTiempoTranscurrido() + "\t"
                        + "TEspera: " + procesos.get(i).getTiempoEspera() + "\t"
                        + "TRespuesta: " + procesos.get(i).getTiempoRespuesta() + "\n"
                );

                if (procesos.get(i).getResult().equals("Error")) {
                    procesosTerminados(procesos.get(i).getId(),
                            "error", op, procesos.get(i).getResult(),
                            procesos.get(i).getTiempoLlegada(),
                            procesos.get(i).getTiempoFinalizacion(),
                            procesos.get(i).getTiempoRetorno(), procesos.get(i).getTiempoEspera(),
                            procesos.get(i).getTiempoTranscurrido(), "0",
                            procesos.get(i).getTiempoRespuesta()
                    );
                } else {
                    procesosTerminados(procesos.get(i).getId(),
                            "normal", op, procesos.get(i).getResult(),
                            procesos.get(i).getTiempoLlegada(),
                            procesos.get(i).getTiempoFinalizacion(),
                            procesos.get(i).getTiempoRetorno(), procesos.get(i).getTiempoEspera(),
                            procesos.get(i).getTiempoTranscurrido(), "0",
                            procesos.get(i).getTiempoRespuesta()
                    );
                }

                /* datosEnTabla(procesos.get(i).getId(), 
                 op, procesos.get(i).getResult(),
                 procesos.get(i).getTiempoLlegada()
                 , procesos.get(i).getTiempoFinalizacion()
                 , procesos.get(i).getTiempoRetorno()
                 , procesos.get(i).getTiempoTranscurrido()
                 , procesos.get(i).getTiempoEspera()
                 , procesos.get(i).getTiempoRespuesta());*/
                procesos.remove(i);
                aListos();
                numeroProcesosM -= 1;
                procesosL.setText(Integer.toString(numeroProcesosM));

                if (numeroProcesosM < 0) {

                    procesosL.setText("0");

                }

            } while (!procesos.isEmpty());

        }

        // tablaDatos.setVisible(true);
        // mostrarDatos();
        procesoEjecucion.setText("");
        lotesEjecucionT.setText("");
        tiempoTranscurridoL.setText("");
        bloqueadosL.setText("0");
        tiempoRespuesta = 1;
        aumenta = false;

    }

    public int dameIndice(String indiceLote) {
        for (int i = 0; i < procesos.size(); i++) {
            if (indiceLote.equals(procesos.get(i).getLote())) {
                return i;
            }
        }
        return 0;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bloqueadosL;
    private javax.swing.JTextArea bloqueadosT;
    private javax.swing.JLabel contadorGlobal;
    private javax.swing.JLabel contadorL;
    private javax.swing.JButton ejecutarB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea lotesEjecucionT;
    private javax.swing.JTextField numeroProcesosT;
    private javax.swing.JTextArea procesoEjecucion;
    private javax.swing.JTextArea procesoTerminadoL;
    private javax.swing.JLabel procesosL;
    private javax.swing.JButton registrarB;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JLabel tiempoRestanteL;
    private javax.swing.JLabel tiempoTranscurridoL;
    // End of variables declaration//GEN-END:variables

}
