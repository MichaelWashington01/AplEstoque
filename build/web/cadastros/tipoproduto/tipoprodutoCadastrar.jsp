<%-- 
    Document   : tipoprodutoCadastrar
    Created on : 01/06/2023, 00:28:51
    Author     : maico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrartipoproduto" action="TipoProdutoCadastrar" method="POST">
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">Cadastro do Tipo de Produto</th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID:</td>
            <td><input type="text" name="idtipoproduto" id="idtipoproduto" value="${tipoproduto.idTipoProduto}" readonly="readonly" ></td></tr>
            <tr><td>Tipo:</td>
            <td><input type="text" name="descricao" id="descricao" value="${tipoproduto.descricao}" size="50" maxlength="50" ></td></tr>
            
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
