import { Tab, TabList, TabPanel, Tabs } from "@mui/joy";

export function NavBar() {
  return (
    <div className="block">
      <Tabs size="lg" orientation="horizontal">
        <TabList disableUnderline>
          <Tab
            variant="outlined"
            color="neutral"
            disableIndicator
            indicatorInset
          >
            ...
          </Tab>
        </TabList>
        <TabPanel>...</TabPanel>
      </Tabs>
    </div>
  );
}