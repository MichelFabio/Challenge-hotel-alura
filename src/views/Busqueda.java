package views;

import controler.HabitacionesController;
import controler.HuespedesController;
import controler.ReservasControler;
import modelo.Huespedes;
import modelo.Reservas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private final JPanel contentPane;
	private final JTextField txtBuscar;
	private final JTable tbHuespedes;
	private final JTable tbReservas;
	private final DefaultTableModel modelo;
	private final DefaultTableModel modeloHuesped;
	private final JLabel labelAtras;
	private final JLabel labelExit;
	int xMouse, yMouse;
	private final ReservasControler reservasControler;
	private final HuespedesController huespedesController;
	private final HabitacionesController habitacionesController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		this.reservasControler = new ReservasControler();
		this.huespedesController = new HuespedesController();
		this.habitacionesController = new HabitacionesController();
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("huesped_id");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		cargarTablaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Identificación");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huespedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		cargarTablaHuespedes();


		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panel.getSelectedIndex() == 0){
					limpiarTablaReservas();
					if(txtBuscar.getText().isEmpty()){
						cargarTablaReservas();
					}else {
						buscarReserva();
					}
				}else{
					limpiarTablaHuespedes();
					if(txtBuscar.getText().isEmpty()){
						cargarTablaHuespedes();
					}else {
						buscarHuesped();
					}


				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelectedIndex() == 0) {
					editarReserva();
					limpiarTablaReservas();
					cargarTablaReservas();
				}else {
					editarHuesped();
					limpiarTablaHuespedes();
					cargarTablaHuespedes();
				}

			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panel.getSelectedIndex() == 0) {

					eliminarReserva();
					limpiarTablaReservas();
					cargarTablaReservas();
				}else {
					eliminarHuesped();
					limpiarTablaHuespedes();
					cargarTablaHuespedes();
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private void editarHuesped() {
		if(tieneFilaElegida()){
			JOptionPane.showMessageDialog(null,"Seleccione un item");
			return;
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					var huespede = new Huespedes();
					huespede.setId( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					huespede.setNombre(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString());
					huespede.setApellido(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString());
					huespede.setFecha_nacimiento((Date) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3));
					huespede.setNacionalidad(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString());
					huespede.setTelefono(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
					//falta huesped
					this.huespedesController.editar(huespede);

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));

	}

	//Busca por el id del huesped
	private void buscarHuesped() {
		if (txtBuscar.getText() != null){
			var huesped = huespedesController.buscarHuesped(txtBuscar.getText());
			modeloHuesped.addRow(new Object[]{
					huesped.getId(),
					huesped.getNombre(),
					huesped.getApellido(),
					huesped.getFecha_nacimiento(),
					huesped.getNacionalidad(),
					huesped.getTelefono()
			});
		}
	}

	private void cargarTablaHuespedes() {
		var huespedes = new HuespedesController().listar();
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[]{
				huesped.getId(),
				huesped.getNombre(),
				huesped.getApellido(),
				huesped.getFecha_nacimiento(),
				huesped.getNacionalidad(),
				huesped.getTelefono()
		}));
	}

	private void eliminarReserva() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					reservasControler.eliminarReserva(id);
					habitacionesController.modificarCuposHabitaciones(id,1);
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	private void eliminarHuesped(){
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					String id = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString();
					if(reservasControler.tieneHuesped(id)){
						JOptionPane.showMessageDialog(null,"Hay reservas asignadas a este usuarios, por favor elimine primero las reservas");
					}else {
						huespedesController.eliminar(id);
					}
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));	}

	private void buscarReserva() {
		if (txtBuscar.getText() != null) {
			Integer id = Integer.parseInt(txtBuscar.getText());
			var reserva = reservasControler.buscarReserva(id);
			modelo.addRow(new Object[]{
					reserva.getIdReservas(),
					reserva.getFecha_entrada(),
					reserva.getFecha_salida(),
					reserva.getValor(),
					reserva.getForma_pago(),
					reserva.getHuesped().getId()
			});

		}else{
			JOptionPane.showMessageDialog(this,"Por favor introduzca el id");
		}


    }

	private void editarReserva() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					var reservas = new Reservas();
					reservas.setIdReservas(Integer.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString()));
					reservas.setFecha_entrada((Date)(modelo.getValueAt(tbReservas.getSelectedRow(), 1)));
					reservas.setFecha_salida((Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2));
					reservas.setValor(Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),3).toString()));
					reservas.setForma_pago(modelo.getValueAt(tbReservas.getSelectedRow(),4).toString());
					//falta huesped
					this.reservasControler.editarReserva(reservas);

				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}

	private void cargarTablaReservas() {
		var reservas = reservasControler.buscarReserva();
		reservas.forEach(reserva -> modelo.addRow(
				new Object[] { reserva.getIdReservas(),
						reserva.getFecha_entrada(),
						reserva.getFecha_salida(),
						reserva.getValor(),
						reserva.getForma_pago(),
						reserva.getHuesped().getId()}));



    }
	private boolean tieneFilaElegida() {
		return (tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0) & (tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0);
	}
	private void limpiarTablaReservas() {

		modelo.getDataVector().clear();
	}
	private void limpiarTablaHuespedes(){
		modeloHuesped.getDataVector().clear();

	}

	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
