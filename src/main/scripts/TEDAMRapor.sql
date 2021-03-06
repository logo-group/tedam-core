
SELECT * FROM dbo.fn_TEDAM_TC_RUN_REPORT('2018-08-24')
order by SortKey,TESTCASE_ID, TESTRUN_ID

/*
alter FUNCTION dbo.fn_TEDAM_TC_RUN_REPORT (@Parameter1 date)
RETURNS TABLE
AS
RETURN
(
select TestCaseName, TESTCASE_ID, TESTRUN_ID, VERSION, TESTRUN_STATUS, EXECUTION_MSG, START_DATE, END_DATE, CREATED_USER, UPDATED_USER, UPDATED_DATE, SortKey 
from 
(
SELECT F.FolderID, space(F.depth*2+2)+ TC.Name as TestCaseName, F.Sortkey, F.Depth, 
TCR.TESTCASE_ID, TCR.ID AS TESTRUN_ID, 

 CASE TCR.EXECUTION_STATUS  
         WHEN 0 THEN 'Failed'  
         WHEN 1 THEN 'Succeeded'  
		 WHEN 2 THEN 'Not Run'  
         WHEN 3 THEN 'Blocked'  
         WHEN 4 THEN 'Caution'  
         ELSE 'Unknown'  
      END as   
  TESTRUN_STATUS, 
  
  cast(TCR.EXECUTION_MESSAGE as nvarchar(4000)) as EXECUTION_MSG, TCR.START_DATE, TCR.END_DATE, TCR.VERSION,
  TC.CREATED_USER, TC.UPDATED_USER, TC.UPDATED_DATE
  FROM [TEDAM].[dbo].[TESTCASE_TESTRUN] AS TCR 
  LEFT OUTER JOIN [TEDAM].[dbo].[TESTCASE] AS TC WITH (NOLOCK) ON TCR.TESTCASE_ID = TC.ID 
  LEFT OUTER JOIN V_TEDAM_TC_FOLDERS AS F WITH (NOLOCK) ON TC.TESTCASE_FOLDER_ID = F.FolderID 
  where 
  TCR.START_DATE>@Parameter1 and 
  TC.PROJECT_ID=1 
  and TCR.IS_DELETED=0
  and TC.IS_DELETED=0
union
select FolderID, FolderName, Sortkey, Depth, '', '', '', '', '', '', '', '', '', ''
from V_TEDAM_TC_FOLDERS F2
where depth<2 or exists (
  select TC2.TESTCASE_FOLDER_ID
  FROM [TEDAM].[dbo].[TESTCASE_TESTRUN] AS TCR2 
  LEFT OUTER JOIN [TEDAM].[dbo].[TESTCASE] AS TC2 WITH (NOLOCK) ON TCR2.TESTCASE_ID = TC2.ID 
  where 
  TCR2.START_DATE>@Parameter1 and 
  TC2.PROJECT_ID=1 
  and TCR2.IS_DELETED=0
  and TC2.IS_DELETED=0
  and CHARINDEX(cast(TC2.TESTCASE_FOLDER_ID as nvarchar(255)), F2.Sortkey) >0 
)

) as Y
--order by SortKey,TESTCASE_ID, TESTRUN_ID
)

*/

  
/*
  alter view V_TEDAM_TC_FOLDERS as
  select ChildId as FolderID, Space(depth*2) + ChildName as FolderName, Sortkey, depth from
  (
  select H.*, Cast(H.Parents as varchar) + '.' + CAST(format(H.ChildId,'0000000') AS VARCHAR) as SortKey, LEN(Parents) - LEN(REPLACE(Parents,'.','')) as depth
  from V_TEDAM_TC_FOLDER_HIERARCHY H
  where PROJECT_ID=1
  ) T
  --order by SortKey
*/  

 
 
/*
create view V_TEDAM_TC_FOLDER_HIERARCHY as
WITH Hierarchy(ChildId, ChildName, ParentId, Parents,PROJECT_ID)
AS
(
    SELECT Id, Name, format(PARENT_FOLDER_ID,'0000000'), CAST('' AS VARCHAR(MAX)), PROJECT_ID
        FROM TEDAM_FOLDER AS FirstGeneration
        WHERE PARENT_FOLDER_ID IS NULL   
		and IS_DELETED=0 
		and FOLDER_TYPE=1 -- test case
    UNION ALL
    SELECT NextGeneration.Id, NextGeneration.Name, format(Parent.ChildId,'0000000'),
     CAST(
	    CASE WHEN Parent.Parents = ''
        THEN(CAST(format(NextGeneration.PARENT_FOLDER_ID,'0000000') AS VARCHAR(MAX)))
        ELSE(Parent.Parents + '.' + CAST(format(NextGeneration.PARENT_FOLDER_ID,'0000000') AS VARCHAR(MAX)))
    END AS VARCHAR(MAX)),  
	NextGeneration.PROJECT_ID
        FROM TEDAM_FOLDER AS NextGeneration
        INNER JOIN Hierarchy AS Parent ON NextGeneration.PARENT_FOLDER_ID = Parent.ChildId  
		where NextGeneration.IS_DELETED=0
		and NextGeneration.FOLDER_TYPE=1 -- test case
)
SELECT *
    FROM Hierarchy
*/
