<%-- 
    Document   : unidademedida
    Created on : 01/06/2023, 14:50:48
    Author     : maico
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
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
          <a class="nav-link" href="${pageContext.request.contextPath}/ProdutoListar">Produto</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/TipoProdutoListar">Tipo Produto</a>
        </li>


        
      </ul>
    </div>
  </div>
</nav>

               <h2>Unidade Medida</h2>
               <table id="datatable" class="display">
                   <thead>
                       <tr>
                           <th align="left">ID</th>
                           <th align="left">Descrição</th>
                           <th align="left">Sigla</th>
                           <th align="right"></th>
                           <th align="right"></th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach var="unidademedida" items="${unidademedidas}">
                           <tr>
                               <td align="left">${unidademedida.idUnidadeMedida}</td>
                               <td align="left">${unidademedida.descricao}</td>
                               <td align="left">${unidademedida.sigla}</td>
                               <td align="center">
                                   <a href="${pageContext.request.contextPath}/UnidadeMedidaExcluir?idUnidadeMedida=${unidademedida.idUnidadeMedida}">
                                       Excluir</a></td>
                                       <td>
                                       <a href="${pageContext.request.contextPath}/UnidadeMedidaCarregar?idUnidadeMedida=${unidademedida.idUnidadeMedida}">
                                       Alterar</a></td>
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
               
               <div align="center">
                   <a href="${pageContext.request.contextPath}/UnidadeMedidaNovo">Novo</a>
                   <a href="index.jsp">Voltar à Página Inicial </a>
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
