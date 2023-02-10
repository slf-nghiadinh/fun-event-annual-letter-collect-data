      *****************************************************************
      **  MEMBER :  CCWL4927                                         **
      **  REMARKS:  MESSAGE INTERFACE FOR DOCID                      **
      **            ANNUAL LETTER                                    **
      **                                                             **
      *****************************************************************
      **  RELEASE   DESCRIPTION                                      **
T01677**  03MAR2021 PHONGP ANNUAL LETTER                             **
H01677**  10AUG2021 PHONGP AS ILP                                    **
I01677**  18JAN2022 PHONGP AS EE, FE                                 **
A01677**  10MAY2022 PHONGP AS ULA                                    **
C01677**  29SEP2022 PHONGP NEW TEMPLATE ILP                          **
      *****************************************************************
       01  L4927-DATA-INFO.
           05  L4927-LETTER-TYP.
               10  L4927-TYP-EE                           PIC X(001).
               10  L4927-TYP-FE                           PIC X(001).
               10  L4927-TYP-UL                           PIC X(001).
               10  L4927-TYP-ILP                          PIC X(001).
               10  L4927-TYP-PA-TERM                      PIC X(001).
                              
           05  L4927-DATE-INFO.
               10  L4927-CURRENT-DT                       PIC X(010).
                              
           05  L4927-POL-INFO.
               10  L4927-POL-ID                           PIC X(010).
               10  L4927-POL-PLAN-ID                      PIC X(006).
               10  L4927-POL-BILL-MODE-CD                 PIC X(010).
               10  L4927-POL-COMNT-TXT                    PIC X(200).
               10  L4927-POL-ANNV-DT                      PIC X(010).
               10  L4927-POL-INST-PREM-AMT                PIC X(025).
               10  L4927-POL-TPREM-AMT                    PIC X(025).
               10  L4927-POL-YEAR                         PIC X(003).
               10  L4927-TOTAL-PAID-PREM                  PIC X(025).
               10  L4927-POL-PREM-SUSP-AMT                PIC X(025).
               10  L4927-POL-NET-BASE-CASH-VALUE          PIC X(025).
               10  L4927-ZPOL-UW-LOAD-PREM                PIC X(025).
               10  L4927-ZPOL-UW-LOAD-EXP-DT              PIC X(010).
                              
           05  L4927-AG-INFO.
               10  L4927-AG-ID                            PIC X(006).

           05  L4927-PLAN-ID-INFO.
               10  L4927-PLAN-TYP-NEW-UL                  PIC X(001).
               10  L4927-PLAN-TYP-ILP-SINGLE              PIC X(001).
               10  L4927-PLAN-TYP-EE                      PIC X(001).
               10  L4927-PLAN-TYP-GPE                     PIC X(001).
               10  L4927-PLAN-TYP-CANCER                  PIC X(001).
               10  L4927-POL-MRRIDER                      PIC X(001).
A01677         10  L4927-PLAN-TYP-ZFND                    PIC X(001).
               10  FILLER                                 PIC X(023).
               
           05  L4927-OWNER-INFO.
               10  L4927-OW-CLI-ID                        PIC X(010).
               10  L4927-OW-NM                            PIC X(100).
               10  L4927-OW-EMAIL-ID                      PIC X(050).
               10  L4927-OW-CEL-PHON-NUM                  PIC X(050).
               10  L4927-OW-ADDR-G.
                   15  L4927-OW-ADDR-T              OCCURS  05 TIMES.
                       20  L4927-OW-ADDR-LN               PIC X(128).
               
           05  L4927-CVG-INFO.
               10  L4927-CVG-CNT                          PIC X(002).
               10  L4927-CVG-G.
                   15  L4927-CVG-T                  OCCURS  40 TIMES.
                       20  L4927-CVG-PLAN-ID              PIC X(006).
C01677                 20  L4927-CVG-RIDER-PLAN-NM        PIC X(200).
                       20  L4927-CVG-LI-ID                PIC X(010).
                       20  L4927-CVG-LI-NM                PIC X(100).
                       20  L4927-CVG-FACE-AMT             PIC X(025).
                       20  L4927-CVG-ISS-DT               PIC X(010).
                       20  L4927-CVG-MAT-XPRY-DT          PIC X(010).
                       20  L4927-CVG-MPREM-AMT            PIC X(025).
                       20  L4927-CVG-MRRIDER              PIC X(001).
                       20  L4927-CVG-PREM-CHNG-DT         PIC X(010).

           05  L4927-POL-LOAN-INFO.
               10  L4927-POL-LOAN-AMT-1                   PIC X(025).  
               10  L4927-POL-LOAN-AMT-2                   PIC X(025).  
               10  L4927-POL-LOAN-AMT-3                   PIC X(025).  
               10  L4927-POL-LOAN-AMT-4                   PIC X(025).  
               10  L4927-POL-LOAN-AMT-5                   PIC X(025).  
                       
           05  L4927-TYP-EE-INFO.
               10  L4927-EE-PRINT-DT                      PIC X(010).  
               10  L4927-EE-TOT-INTEREST                  PIC X(025).  
I01677         10  L4927-EE-PREM-CHNG-DT                  PIC X(010).  
I01677         10  L4927-EE-EOPREM-TERM                   PIC X(001).  
I01677         10  L4927-EE-EBN-PAYOUT                    PIC X(025).  
               10  FILLER                                 PIC X(014).

           05  L4927-TYP-FE-INFO.
               10  L4927-FE-PRINT-DT                      PIC X(010).  
               10  L4927-FE-CASH-COUPON                   PIC X(025).  
               10  FILLER                                 PIC X(050).
               
           05  L4927-TYP-PA-TERM-INFO.
               10  L4927-PA-TERM-PRINT-DT                 PIC X(010).  
               10  L4927-PA-TERM-CASH-COUPON              PIC X(025).  
               10  L4927-PA-TERM-LOYALTY-AMT              PIC X(025).  
               10  L4927-PA-TERM-HEATHCARE                PIC X(025).    
               10  FILLER                                 PIC X(050).
               
           05  L4927-TYP-UL-INFO.
               10  L4927-UL-PRINT-DT                      PIC X(010).  
               10  L4927-UL-PREM-AMT-1                    PIC X(025).  
               10  L4927-UL-PREM-AMT-2                    PIC X(025).  
               10  L4927-UL-PREM-AMT-3                    PIC X(025).  
               10  L4927-UL-PREM-AMT-4                    PIC X(025).  
               10  L4927-UL-PREM-AMT-5                    PIC X(025).  
               10  L4927-UL-PREM-AMT-6                    PIC X(025).  
               10  L4927-UL-PREM-AMT-7                    PIC X(025).  
               10  L4927-UL-PREM-AMT-8                    PIC X(025).  
               10  L4927-UL-PREM-AMT-9                    PIC X(025).  
               10  L4927-UL-PREM-AMT-10                   PIC X(025).  
               10  L4927-UL-PREM-AMT-11                   PIC X(025).  
               10  L4927-UL-PREM-AMT-12                   PIC X(025).  
               10  L4927-UL-PREM-AMT-13                   PIC X(025).  
               10  L4927-UL-PREM-AMT-14                   PIC X(025).  
               10  L4927-UL-PREM-AMT-A                    PIC X(025).  
               10  L4927-UL-PREM-AMT-B                    PIC X(025).  
               10  L4927-UL-PREM-AMT-C                    PIC X(025).  
               10  L4927-UL-PREM-AMT-D                    PIC X(025).  
               10  L4927-UL-PREM-AMT-E                    PIC X(025).  
               10  L4927-UL-PREM-AMT-15                   PIC X(025).  
               10  L4927-UL-PREM-AMT-16                   PIC X(025).  
               10  FILLER                                 PIC X(050).
               
           05  L4927-TYP-ILP-INFO.
               10  L4927-ILP-PRINT-DT                     PIC X(010).  
               10  L4927-PREV-POL-ANNV-DT                 PIC X(010).
               10  L4927-ILP-PREM-AMT-1                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-2                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-3                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-4                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-5                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-6                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-7                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-8                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-9                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-10                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-11                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-12                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-13                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-14                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-15                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-16                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-17                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-18                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-19                  PIC X(025).  
               10  L4927-ILP-PREM-AMT-7-SINGLE            PIC X(025).  
               10  L4927-ILP-PREM-AMT-13-SINGLE           PIC X(025).  
               10  L4927-ILP-PREM-AMT-14-SINGLE           PIC X(025).  
               10  L4927-ILP-PREM-AMT-15-SINGLE           PIC X(025).  
               10  L4927-ILP-PREM-AMT-D                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-E                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-F                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-G                   PIC X(025).  
               10  L4927-ILP-PREM-AMT-H                   PIC X(025).  
               10  FILLER                                 PIC X(050).
               
           05  L4927-ILP-FUND-INFO.
               10  L4927-F-INFO-1.
                   15  L4927-F1-T                   OCCURS  03 TIMES.
                       20  L4927-F1-UNIT                  PIC X(025).
                       20  L4927-F1-VALUE                 PIC X(025).
               10  L4927-F-INFO-2.
                   15  L4927-F2-T                   OCCURS  03 TIMES.
                       20  L4927-F2-UNIT                  PIC X(025).
                       20  L4927-F2-VALUE                 PIC X(025).
               10  L4927-F-INFO-3.
                   15  L4927-F3-T                   OCCURS  03 TIMES.
                       20  L4927-F3-UNIT                  PIC X(025).
                       20  L4927-F3-VALUE                 PIC X(025).
               10  L4927-F-INFO-4.
                   15  L4927-F4-T                   OCCURS  03 TIMES.
                       20  L4927-F4-UNIT                  PIC X(025).
                       20  L4927-F4-FUND-PRICE            PIC X(025).
                       20  L4927-F4-VALUE                 PIC X(025).
                       
           05  L4927-ILP-ADMIN-INFO.
               10  L4927-TOTAL-ALLOCATE-CHARGE            PIC X(025).
               10  L4927-TOTAL-COI-CHARGE                 PIC X(025).
               10  L4927-TOTAL-ADMIN-CHARGE               PIC X(025).
               10  L4927-TOTAL-WITHDRAWAL-CHARGE          PIC X(025).
               10  L4927-TOTAL-SWITCHING-FEE              PIC X(025).
               10  L4927-ADMIN-T                    OCCURS  12 TIMES.
                   15  L4927-ILP-MONTH                    PIC X(025).
                   15  L4927-ILP-ALLOCATE-CHARGE          PIC X(025).
                   15  L4927-ILP-COI-CHARGE               PIC X(025).
                   15  L4927-ILP-ADMIN-CHARGE             PIC X(025).
                   15  L4927-ILP-WITHDRAWAL-CHARGE        PIC X(025).
                   15  L4927-ILP-SWITCHING-FEE            PIC X(025).
                       
H01677     05  L4927-TFV-INFO.
H01677         10  L4927-ILP-FV-PRICE-DT                  PIC X(010).  
H01677         10  L4927-ILP-FA-PRICE-DT                  PIC X(010).  
H01677
I01627     05  L4927-POL-CSTAT-CD                         PIC X(001).
A01677     05  L4927-AGT-CHNL-CD                          PIC X(010).
A01677     05  L4927-ULA-INFO.
A01677         10  L4927-UL-ULA-PREM-AMT-A1               PIC X(025).  
A01677         10  L4927-UL-ULA-PREM-AMT-A2               PIC X(025).  
A01677         10  L4927-UL-ULA-PREM-AMT-D9               PIC X(025).  
A01677         10  L4927-UL-ULA-PREM-AMT-E12              PIC X(025).  
A01677         10  L4927-UL-ULA-PREM-AMT-15-1             PIC X(025).  
A01677         10  L4927-UL-ULA-PREM-AMT-15-2             PIC X(025).  
A01677
C01677     05  L4927-ILP-DEP-INCR-G.
C01677         10  L4927-ILP-DEP-INCR-T             OCCURS  03 TIMES.
C01677             15  L4927-ILP-DEP-INCR-UNIT            PIC X(025).
C01677
C01677     05  L4927-ILP-BON-INCR-G.
C01677         10  L4927-ILP-BON-INCR-T             OCCURS  03 TIMES.
C01677             15  L4927-ILP-BON-INCR-UNIT            PIC X(025).
C01677
C01677     05  L4927-ILP-XFER-INCR-G.
C01677         10  L4927-ILP-XFER-INCR-T            OCCURS  03 TIMES.
C01677             15  L4927-ILP-XFER-INCR-UNIT           PIC X(025).
C01677
C01677     05  L4927-ILP-XFER-INCR-AMT                    PIC X(025).
C01677             
C01677     05  L4927-ILP-ADM-DECR-G.
C01677         10  L4927-ILP-ADM-DECR-T             OCCURS  03 TIMES.
C01677             15  L4927-ILP-ADM-DECR-UNIT            PIC X(025).
C01677             
C01677     05  L4927-ILP-WDR-DECR-G.
C01677         10  L4927-ILP-WDR-DECR-T             OCCURS  03 TIMES.
C01677             15  L4927-ILP-WDR-DECR-UNIT            PIC X(025).
C01677             
C01677     05  L4927-ILP-XFER-DECR-G.
C01677         10  L4927-ILP-XFER-DECR-T            OCCURS  03 TIMES.
C01677             15  L4927-ILP-XFER-DECR-UNIT           PIC X(025).
C01677
C01677     05  L4927-ILP-XFER-DECR-AMT                    PIC X(025).
C01677             
C01677     05  L4927-SPLIT-RIDER-FUND                     PIC X(001).
C01677             
           05  FILLER                                     PIC X(010).
       
      *****************************************************************
      **                END OF COPYBOOK CCWL4927                     **
      *****************************************************************
