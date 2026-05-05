<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:pmd="http://pmd.sourceforge.net/report/2.0.0">

  <xsl:param name="linkPrefix" select="'../docs/src-html/'"/>

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>PMD Report</title>
        <style>
          body { font-family: Arial, sans-serif; margin: 2em; }
          table { border-collapse: collapse; width: 100%; }
          th, td { border: 1px solid #ccc; padding: 0.4em; vertical-align: top; }
          th { background: #eee; text-align: left; }
        </style>
      </head>
      <body>
        <h1>PMD Report</h1>
        <table>
          <tr>
            <th>File</th>
            <th>Line</th>
            <th>Rule</th>
            <th>Description</th>
          </tr>

          <xsl:for-each select="/pmd:pmd/pmd:file/pmd:violation">
            <tr>
              <td>
                <a href="{$linkPrefix}{substring-before(substring-after(../@name, 'src/'), '.java')}.html#line.{@beginline}">
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
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
