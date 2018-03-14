package com.ionic.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ionic.cursomc.domain.Categoria;
import com.ionic.cursomc.domain.Cidade;
import com.ionic.cursomc.domain.Cliente;
import com.ionic.cursomc.domain.Endereco;
import com.ionic.cursomc.domain.Estado;
import com.ionic.cursomc.domain.ItemPedido;
import com.ionic.cursomc.domain.Pagamento;
import com.ionic.cursomc.domain.PagamentoComBoleto;
import com.ionic.cursomc.domain.PagamentoComCartao;
import com.ionic.cursomc.domain.Pedido;
import com.ionic.cursomc.domain.Produto;
import com.ionic.cursomc.domain.enums.EstadoPagamento;
import com.ionic.cursomc.domain.enums.TipoCliente;
import com.ionic.cursomc.repositories.CategoriaRepository;
import com.ionic.cursomc.repositories.CidadeRepository;
import com.ionic.cursomc.repositories.ClienteRepository;
import com.ionic.cursomc.repositories.EnderecoRepository;
import com.ionic.cursomc.repositories.EstadoRepository;
import com.ionic.cursomc.repositories.ItemPedidoRepository;
import com.ionic.cursomc.repositories.PagamentoRepository;
import com.ionic.cursomc.repositories.PedidoRepository;
import com.ionic.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minhas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "marias@hotmail.com", "3637891277", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));		
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod2));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ipt1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ipt2 = new ItemPedido(ped2, prod3, 0.00, 2, 80.00);
		ItemPedido ipt3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ipt1, ipt2));
		ped2.getItens().addAll(Arrays.asList(ipt3));
		
		prod1.getItens().addAll(Arrays.asList(ipt1));
		prod2.getItens().addAll(Arrays.asList(ipt3));
		prod3.getItens().addAll(Arrays.asList(ipt2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ipt1, ipt2, ipt3));
		
	}
}
