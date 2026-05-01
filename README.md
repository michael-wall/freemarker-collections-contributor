## Information Template snippet using journalArticleAssetEntryHelper ##

```
<#assign infoItemReference = request.getAttribute("INFO_ITEM_REFERENCE")!{}>	
<#assign assetEntryId = infoItemReference.getInfoItemIdentifier().getClassPK()!0>
<#assign journalArticle = journalArticleAssetEntryHelper.getJouralArticleByAssetEntryId(assetEntryId)!>

<#if journalArticle?? && journalArticle?has_content>
    <@liferay_journal["journal-article"]
        articleId=journalArticle.getArticleId()
        ddmTemplateKey='6244802'
        groupId=scopeGroupId
    />
</#if>

```

## Information Template snippet using assetEntryLocalService and journalArticleLocalService ##

```
<#assign infoItemReference = request.getAttribute("INFO_ITEM_REFERENCE")!{}>	
<#assign assetEntryId = infoItemReference.getInfoItemIdentifier().getClassPK()!0>
<#assign assetEntry = assetEntryLocalService.fetchAssetEntry(assetEntryId)!>

<#if assetEntry?? 
    && assetEntry?has_content
    && assetEntry.getClassName()?lower_case == "com.liferay.journal.model.journalarticle">

    <#assign journalArticle = journalArticleLocalService.fetchLatestArticle(assetEntry.getClassPK())!>

    <#if journalArticle?? && journalArticle?has_content>
        <@liferay_journal["journal-article"]
            articleId=journalArticle.getArticleId()
            ddmTemplateKey='6244802'
            groupId=scopeGroupId
        />
    </#if>
</#if>
```
