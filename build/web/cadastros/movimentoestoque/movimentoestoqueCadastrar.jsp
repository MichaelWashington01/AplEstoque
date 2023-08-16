<%-- 
    Document   : movimentoestoqueCadastrar
    Created on : 09/06/2023, 18:54:59
    Author     : maico
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<form name="cadastrarmovimentoestoque" action="MovimentoEstoqueCadastrar"
      method="POST">
          
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro Movimento Estoque
                </th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
                <tr><td>ID:</td>
                <td><input type="text" name="idmovimento" id="idmovimento" value="${movimentoestoque.idMovimento}" readonly="readonly"/></td></tr>
            <tr><td>Entrada Saida:</td>
                <td><input type="text" name="entradasaida" id="entradasaida" value="${movimentoestoque.entradaSaida}"  size="50" maxlength="50"/></td></tr>
             <tr><td>Documento:</td>
                <td><input type="text" name="documento" id="documento" value="${movimentoestoque.documento}"  size="50" maxlength="50"/></td></tr>
              <tr><td>Data:</td>
                <td><input type="text" name="data" id="data" value="${movimentoestoque.data}"  size="50" maxlength="50"/></td></tr>
               <tr><td>Quantidade:</td>
                <td><input type="text" name="quantidade" id="quantidade" value="${movimentoestoque.quantidade}"  size="50" maxlength="50"/></td></tr>
                <tr><td>Valor Movimento:</td>
                <td><input type="text" name="valormovimento" id="valormovimento" value="${movimentoestoque.valorMovimento}"  size="50" maxlength="50"/></td></tr>
            
            <tr>
                <td>Produto: </td>
                <td>
                <select name="idproduto" id="idproduto">
                    <option value="">selecione</option>
                    <c:forEach var="produto" items="${produtos}">
                        <option value="${produto.idProduto}" 
                                ${movimentoEstoque.produto.idProduto == produto.idProduto ? "selected" : ""} >
                            ${produto.nomeProduto} </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td>Tipo Movimento: </td>
                <td>
                <select name="idtipomovimento" id="idtipomovimento">
                    <option value="">selecione</option>
                    <c:forEach var="tipomovimento" items="${tipomovimentos}">
                        <option value="${tipomovimento.idTipoMovimento}" 
                                ${movimentoEstoque.tipomovimento.idTipoMovimento == tipomovimento.idTipoMovimento ? "selected" : ""} > 
                            ${tipomovimento.descricao} </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td>Funcionario: </td>
                <td>
                <select name="idfuncionario" id="idfuncionario">
                    <option value="">selecione</option>
                    <c:forEach var="funcionario" items="${funcionarios}">
                        <option value="${funcionario.idFuncionario}" 
                                ${movimentoEstoque.funcionario.idFuncionario == funcionario.idFuncionario ? "selected" : ""} > 
                            ${funcionario.nomeFuncionario} </option>
                    </c:forEach>
                </select>
                </td>
                
            </tr>
            
        
            <tr><td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                    <input type="reset" name="limpar" id="limpar" value="Limpar"/>       
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar a Página Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
    
</form>
    <%@ include file="/footer.jsp"%> 
