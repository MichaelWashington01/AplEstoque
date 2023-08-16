<%-- 
    Document   : produto
    Created on : 01/06/2023, 22:40:06
    Author     : maico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Cadastro</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor02">
      <ul class="navbar-nav me-auto">

                  <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/index.jsp">Home
          </a>
        </li>
          
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/TipoProdutoListar">Tipo Produto</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/UnidadeMedidaListar">Unidade Medida</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/MovimentoEstoqueListar">Movimento Estoque</a>
        </li>

        
      </ul>
    </div>
  </div>
</nav>

    
    
    <h2>Produtos</h2>
    <div class="col-12 panel-body">
    <table id="datatable" class="table table-striped table-bordered basic-datatable">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Produto</th>
                <th align="left">Ultimo Preço</th>
                <th align="left">Saldo</th>
                <th align="left">Tipo</th>
                <th align="left">Unidade</th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>
            
        </thead>
        <tbody>
            <c:forEach var="produto" items="${produtos}">
                <tr>
                    <td align="left">${produto.idProduto}</td>
                    <td align="left">${produto.nomeProduto}</td>
                    <td align="left">${produto.ultimoPrecoPago}</td>
                    <td align="left">${produto.saldoAtual}</td>
                    <td align="left">${produto.tipoproduto.descricao}</td>
                    <td align="left">${produto.unidademedida.sigla}</td>
                    <td align="center">
                        <a href=
                           "${pageContext.request.contextPath}/ProdutoExcluir?idProduto=${produto.idProduto}">
                            <button class="btn btn-group-lg 
                                    <c:out value="${produto.idProduto == 'produto' ? 'btn-danger': 'btn-success'}"/>">
                                <c:out value="${produto.idProduto == 'produto' ? 'Inativar' : 'Excluir'}"/>
                            </button>
                            </a>
                    </td>
                            <td align="center">   
                                <a href=
                                   "${pageContext.request.contextPath}/ProdutoCarregar?idProduto=${produto.idProduto}">
                                    <button class="btn btn-group-lg btn-success"/>Alterar</button>
                                </a>
                               </td>       
                        </tr>
            </c:forEach>    
        </tbody>
        
    </table>
    </div>
    <div align="center">
        <a href="${pageContext.request.contextPath}/ProdutoNovo">Novo</a>
        <a href="index.jsp">Voltar à Página inicial</a>
    </div>
        
        <script>
            
            $(document).ready(function(){
              console.log('entrei ready');  
                $('#datatable').DataTable({
                   "oLanguage":{
                       "sProcessing": "Processando...",
                       "sLengthMenu": "Mostrar _MENU_registros", 
                       "sZeroRecords": "Nenhum registro encontrado.",
                       "sInfo": "Mostrando de _START_até _END_de _TOTAL_registros",
                       "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                       "sInfoFiltered": "",
                       "sInfoPostFix": "",
                       "sSearch": "Buscar:",
                       "sUrl": "",
                       
                       "oPaginate": {
                           "sFirst": "Primeiro",
                           "sPrevious": "Anterior",
                           "sNext": "Seguinte",
                           "sLast": "Último"
                       }
                   }          
               });
            }); 
            
        </script>
        
  
        <%@include file="/footer.jsp"%>
        
