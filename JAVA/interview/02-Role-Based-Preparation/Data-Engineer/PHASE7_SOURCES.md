# Phase 7 Data-Engineer sources

Primary sources directly opened on **2026-11-03**. Original exercises, role emphasis and sample workloads are [INFERRED]; these pages establish mechanisms, not employer questions, measured outcomes or candidate readiness. Rolling documentation is not a pinned runtime contract.

| ID | Title / publisher | URL | Publication/update | Accessed | Type / reliability | Scope and limits |
|---|---|---|---|---|---|---|
| P7-DATA-01 | Airflow best practices | [Source](https://airflow.apache.org/docs/apache-airflow/stable/best-practices.html) | Apache Airflow; rolling stable docs | 2026-11-03 | Primary technical/first-party engineering guidance; high for stated mechanism | Retry/partition task discipline; no DAG executed |
| P7-DATA-02 | Beam programming guide | [Source](https://beam.apache.org/documentation/programming-guide/) | Apache Beam; rolling docs | 2026-11-03 | Primary technical/first-party engineering guidance; high for stated mechanism | Event time, windowing and pipeline model; no runner tested |
| P7-DATA-03 | PostgreSQL window functions | [Source](https://www.postgresql.org/docs/18/tutorial-window.html) | PostgreSQL18; page date unstated | 2026-11-03 | Primary technical/first-party engineering guidance; high for stated mechanism | Partition/order semantics; no native SQL execution |
| P7-DATA-04 | Spark Structured Streaming overview | [Source](https://spark.apache.org/docs/latest/streaming/index.html) | Apache Spark; rolling latest docs | 2026-11-03 | Primary technical/first-party engineering guidance; high for stated mechanism | Incremental stream model; guarantees depend on mode/source/sink and custom effects |
| P7-DATA-05 | dbt incremental models | [Source](https://docs.getdbt.com/docs/build/incremental-models-overview) | dbt Labs; rolling docs | 2026-11-03 | Primary technical/first-party engineering guidance; high for stated mechanism | Incremental-model mechanism; actual merge support depends on adapter/configuration |
| P7-DATA-06 | Spark SQL performance tuning | [Source](https://spark.apache.org/docs/latest/sql-performance-tuning.html) | Apache Spark; rolling latest docs | 2026-11-03 | Primary technical/first-party engineering guidance; high for stated mechanism | Partitions, join planning and skew; no cluster benchmark |

| P7-DATA-07 | Grain / Kimball Group | [Source](https://www.kimballgroup.com/data-warehouse-business-intelligence-resources/kimball-techniques/dimensional-modeling-techniques/grain/) | Author/publisher technique; page date not stated | 2026-11-03 | Primary dimensional-modeling guidance; high for stated concept | Fact-table row grain; original temporal/correction examples remain reasoned guidance |

No product, pipeline, registry, account or user data was accessed. Diagrams/traces and sample calculations are teaching guidance, not native/integration proof. [Role index](README.md).
