<xsl:for-each select="/pmd:pmd/pmd:file/pmd:violation">
  <tr>
    <td>
      <a href="xref/{substring-after(../@name, 'src/')}.html#{@beginline}">
        <xsl:value-of select="substring-after(../@name, 'src/')"/>
      </a>
    </td>
    
    <td><xsl:value-of select="@beginline"/></td>
    
    <td>
      <a href="{@externalInfoUrl}">
        <xsl:value-of select="@rule"/>
      </a>
    </td>
    
    <td><xsl:value-of select="normalize-space(.)"/></td>
  </tr>
</xsl:for-each>
