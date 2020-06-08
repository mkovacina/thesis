xmllint --format destruction-noformat.xml  > destruction.xml
xmllint --format collection-noformat.xml   > collection.xml
xmllint --format manipulation-noformat.xml > manipulation.xml

perl -p -i -e 's/dumpster/goal/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/pick-up-garbage/pick-up/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/put-down-garbage/put-down/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/garbage/object-c/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/partially-disarmed-mine/object-d-partial/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/armed-mine/object-d-untouched/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/disarm-mine-stage-1/first-attack/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/disarm-mine-stage-2/second-attack/g' destruction.xml collection.xml manipulation.xml
perl -p -i -e 's/mine/object-d/g' destruction.xml collection.xml manipulation.xml
