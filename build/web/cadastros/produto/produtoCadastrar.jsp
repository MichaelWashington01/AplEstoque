<%-- 
    Document   : produtoCadastrar
    Created on : 02/06/2023, 00:45:51
    Author     : maico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrarproduto" action="ProdutoCadastrar" method="POST">
    <table>
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Produto</th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID:</td>
                <td><input type="text" name="idproduto" id="idproduto" value="${produto.idProduto}" readonly="readonly"/></td></tr>
            <tr><td>Nome Produto:</td>
                <td><input type="text" name="nomeproduto" id="nomeproduto" value="${produto.nomeProduto}"  size="50" maxlength="50"/></td></tr>
            
            
            <tr>
                <td>Unidade Medida:</td>
                <td>
                    <select name="idunidademedida" id="idunidademedida">
                        <option value="">Selecione</option>
                        <c:forEach var="unidademedida" items="${unidademedidas}">
                            <option value="${unidademedida.idUnidadeMedida}"
                                    ${produto.unidademedida.idUnidadeMedida == unidademedida.idUnidadeMedida ? "selected" : ""}>
                                    ${unidademedida.sigla}
                            </option>
                        </c:forEach>
                </td>
            </tr>
                        <tr>
                <td>Tipo Produto:</td>
                <td>
                    <select name="idtipoproduto" id="idtipoproduto">
                        <option value="">Selecione</option>
                        <c:forEach var="tipoproduto" items="${tipoprodutos}">
                            <option value="${tipoproduto.idTipoProduto}"
                                    ${produto.tipoproduto.idTipoProduto == tipoproduto.idTipoProduto ? "selected" : ""}>
                                    ${tipoproduto.descricao}
                            </option>
                        </c:forEach>
                </td>
            </tr>

            

            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                    <input type="reset" name="limpar" id="limpar" value="Limpar"/>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar à Página inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>
                 <%@include file="/footer.jsp"%>
