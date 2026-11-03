# SRE fundamentals: user objectives and error budgets

## Learning goal and scope

Site reliability engineering treats reliability as a measurable engineering responsibility with explicit tradeoffs. A service-level indicator measures selected user outcomes; a service-level objective declares a target over a window. An error budget describes the permitted bad portion under that definition, not permission to ignore individual serious incidents.

This is early-career teaching, with specialist depth optional. Workloads and traces below are original **[INFERRED]** fixtures, not production observations or employer questions.

## Mechanism and decision method

Write the indicator population and event classification first. For a request-based 99.9% success objective over 1,000,000 eligible requests, the stipulated budget is 1,000 bad requests. This is not the same as 43.2 minutes of outage unless a time-based indicator and 30-day window are explicitly chosen. Use burn rate to compare observed bad-event rate with allowed rate; define alert windows and action owner before paging.

## Worked example

Fictional trace: a service meets average monthly success but burns much of the remaining budget in a short release regression. A multi-window alert can detect meaningful sustained impact sooner than waiting for a monthly target breach. A dependency health alarm may help diagnosis, while the paging condition should relate to actionable user harm. A green dashboard cannot establish recovery if telemetry is missing.

## Tradeoffs, failure and access boundaries

Eliminating toil means removing repetitive manual operational work through a safe mechanism, not automating unknown repairs blindly. Backups need restore/reconciliation evidence, and a redundancy diagram needs failure tests. Balance reliability work, feature speed and cost using the agreed risk context; no universal availability target fits all products.

## Attempt first — OPS7-SRE-FUNDAMENTALS

For a 99.9% request-success target, an observed 1% bad-request rate occurs. What is the burn rate under the same population definition?

<details><summary>Expected reasoning and followups — reveal after attempting</summary>

The allowed bad rate is 0.1%=0.001; observed rate 0.01 divided by 0.001 gives 10× burn. Duration and traffic determine budget consumption; a 10× rate alone does not establish the exact remaining budget or a fixed outage duration.

**Followups:** Why can averaging daily success rates misweight traffic? What evidence proves a restoration works?

**Review:** A strong answer names the actual boundary, derives its consequence and states a check that could change the conclusion. Do not claim a fixture calculation is a measured service result.

</details>

## Evidence and references

- [Alerting on SLOs](https://sre.google/workbook/alerting-on-slos/) — Google SRE Workbook;2018; scope: Error budgets and alert intent; not a supplied production SLO.

Primary references directly inspected **2026-11-03**. [Source register](PHASE7_SOURCES.md). This chapter supplies reviewed reasoning only: no host fault, network capture, image build, workflow, cluster, infrastructure plan, cloud account action or deployment was executed. Version-specific defaults and region/tier support need verification for an implementation.

[Cloud study index](README.md) · [Practice question bank](../12-Interview-Question-Bank/Cloud_DevOps_Questions.md).
